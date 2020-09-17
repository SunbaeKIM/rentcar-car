package Encar;

import Encar.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired CarRepository carRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentCanceled_QuantityChange(@Payload RentCanceled rentCanceled){

        if(rentCanceled.isMe()){
            System.out.println("##### listener QuantityChange : " + rentCanceled.toJson());
            // 취소이벤트 전달받아 재고 원복작업
            Optional<Car> optionalCar = carRepository.findById(rentCanceled.getCarId());
            Car car = optionalCar.orElseGet(Car::new);
            car.setQuantity(car.getQuantity()+1);
            carRepository.save(car);
        }
    }

}
