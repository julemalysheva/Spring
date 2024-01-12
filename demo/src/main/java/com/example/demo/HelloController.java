package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Здесь @RestController — это аннотация Spring, которая говорит, что этот класс
 * является контроллером, и он должен обрабатывать веб-запросы. @GetMapping("/") -
 * это другая аннотация Spring, которая указывает, что метод hello() должен
 * обрабатывать HTTP-запросы GET на URL “/”.
 */

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello. Spring Boot!";
    }
}
