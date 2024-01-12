package com.example.demo;

// точка входа в приложение, как взаимодействовать из вне
// RestController для отработки внешних запросов

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    // к моменту запуска метода у нас уже должен быть бин, экз-р Car.
    // поэтому мы пишем

    @Autowired
    Car car;

    /**
     * чтобы вызвать метод извне нам нужно пометить его
     * аннотацией GetMapping
     * @return
     */
    @GetMapping("/car")
    public String startCar() {
        car.start();
        return "Автомобиль запущен";
    }
}
