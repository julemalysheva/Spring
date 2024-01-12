package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public Engine() {
        System.out.println("Двигатель запущен");
    }

    public void go(){
        System.out.println("Поехали!");
    }
}
