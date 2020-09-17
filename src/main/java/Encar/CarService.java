package Encar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public String checkQuantityByCarId(String carId) {

        Optional<Car> optionalCar = carRepository.findById(Long.parseLong(carId));
        Car car = optionalCar.orElseGet(Car::new);
        // 상품이 없을경우 재고0으로 전달
        if(car.getQuantity() == null) car.setQuantity(0);
        // 상품재고가 있는 경우 재고 -1 하고 할당으로 이벤트 전달
        if( car.getQuantity() > 0 ){
            car.setQuantity(car.getQuantity()-1);
            carRepository.save(car);
            //car.pulishQuantityChecked();
        }
        return car.getQuantity().toString() ;
    }
}
