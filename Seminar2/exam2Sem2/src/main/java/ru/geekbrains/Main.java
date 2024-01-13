package ru.geekbrains;

import ru.geekbrains.config.ProjectConfig;
import ru.geekbrains.domain.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.domain.Engin;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Car c = context.getBean(Car.class);
        Engin e = context.getBean(Engin.class);

        System.out.println(c);
        System.out.println(e);
    }

}

