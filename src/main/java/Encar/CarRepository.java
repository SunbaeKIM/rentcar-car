package Encar;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, Long>{

    //Optional<Car> findByCarId(Long carId);
}