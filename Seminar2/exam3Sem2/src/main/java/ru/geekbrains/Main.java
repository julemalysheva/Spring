package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.config.ProjectConfig;
import ru.geekbrains.domain.Car;
import ru.geekbrains.domain.DiselEngin;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        // System.out.println("Hello world!");
        Car p = context.getBean(Car.class);
        p.go();
        System.out.println("----------------------");
        DiselEngin dEng = context.getBean(DiselEngin.class);
    }
}