package config;

import domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {

    @Bean("BMW")
    Car car1()
    {
        Car obCar = new Car();
        obCar.setModel("X1");
        obCar.setMade("BMW");
        return obCar;
    }

    @Bean(name="Audi")
    Car car2()
    {
        Car obCar = new Car();
        obCar.setModel("S8");
        obCar.setMade("Audi");
        return obCar;
    }

    /**
     * Аннотация @Primary используется в Spring для указания основного (первичного) бина,
     * когда в контексте приложения присутствует несколько бинов одного и того же типа.
     * @return
     */
    @Bean
    @Primary
    Car car3()
    {
        Car obCar = new Car();
        obCar.setModel("H7");
        obCar.setMade("HAVAL");
        return obCar;
    }

    @Bean
    String hello() {
        return "Hello";
    }

    @Bean
    Integer ten() {
        return 10;
    }

}

/**
 * Аннотация @Primary также может быть использована вместе с аннотацией @Qualifier,
 * чтобы уточнить, какой конкретный основной бин должен быть внедрен. Например:
 * @Service
 * @Primary
 * @Qualifier("primary")
 * public class PrimaryService implements SomeService {
 *     // ...
 * }
 *
 * @Service
 * @Qualifier("secondary")
 * public class SecondaryService implements SomeService {
 *     // ...
 * }
 *
 * public class MyComponent {
 *     private final SomeService service;
 *
 *     @Autowired
 *     public MyComponent(@Qualifier("primary") SomeService service) {
 *         this.service = service;
 *     }
 * }
 */