package Encar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarService postsService;
    @GetMapping("/cars/check")
    public String carCheck(@RequestParam String carId){
        return postsService.checkQuantityByCarId(carId);
    }

}
