package ru.geekbrains.domain;

import org.springframework.stereotype.Component;

@Component
//@Lazy
public class DiselEngin  {
    public DiselEngin() {
        System.out.println("Создан экземпляр DiselEngin");
    }

    // @Override
    public void startEngine() {
        System.out.println("Запущен Дизел 65р.л");
    }
}

//@Lazy ленивая загрузка
/*
* Аннотация @Lazy используется в Spring для отложенной инициализации бина.
* Когда бин помечается аннотацией @Lazy,
* Spring будет создавать экземпляр этого бина только когда он будет запрошен впервые.*/