package ru.geekbrains.config;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.geekbrains.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.geekbrains.domain.Engin;

@Configuration
//@ComponentScan(basePackages = "ru.geekbrains.domain")
public class ProjectConfig {

    @Bean
    Engin engin1()
    {
        Engin eng = new Engin();
        eng.setTypeEngin("Бензиновый");
        return eng;
    }

    @Bean
    Engin engin2()
    {
        Engin eng = new Engin();
        eng.setTypeEngin("Дизельный");
        return eng;
    }


    @Bean
    @Primary
    Car car(@Qualifier("engin2") Engin eng)
    {
        Car obCar = new Car(eng);
        //obCar.setCarEngin(engin());
        obCar.setModel("S8");
        obCar.setMade("Audi");
        return obCar;
    }

    @Bean
    Car car2(@Qualifier("engin1") Engin eng)
    {
        Car obCar = new Car(eng);
        //obCar.setCarEngin(engin());
        obCar.setModel("S8");
        obCar.setMade("Audi");
        return obCar;
    }


}