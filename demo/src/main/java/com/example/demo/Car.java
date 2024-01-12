package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//Если мы хотим, чтобы SpringIoC контейнер управлял нашим объектом Car,
//        мы можем аннотировать его аннотацией @Component:

/**
 * Теперь Spring IoC контейнер создаст экземпляр класса Car для нас. Но что, если наш
 * класс Car зависит от класса Engine? Мы можем использовать аннотацию @Autowired
 * для того, чтобы Spring IoC контейнер внедрил (или “подключил”) экземпляр Engine
 * в наш Car:
 */
//@Component - можем заменить на аннотацию Service
@Service
public class Car {
    Engine engine;

    /**
     * Здесь Engine также должен быть аннотирован аннотацией @Component, чтобы
     * Spring IoC контейнер мог создать экземпляр этого класса. Это и есть Dependency
     * Injection (DI) — другой важный принцип в Spring, который позволяет нам легко
     * управлять зависимостями между объектами.
     * @param engine
     */

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
        engine.go();
    }

//    public void setEngine(Engine engine) {
//        this.engine = engine;
//        engine.go();
//    }

    public void start() {
        engine.go();
    }

}

// нигде вручную объект не создается через new, Спринг делает это сам на основе аннотаций
// всегда стараемся внедрять зависимости через конструктор, редко через сеттер
// 3-й способ внедрения зависимостей - через поле сразу автоматически
/**
 * @Autowired
 * Engine engine;
 */