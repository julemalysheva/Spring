package com.example.demosecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Метод addViewControllers() (который переопределяет одноименный метод в WebMvcConfigurer) добавляет
 * четыре контроллера представления. Два контроллера представления ссылаются на представление, имя которого
 * home (определено в home.html), а другой ссылается на представление с именем hello (определено в hello.html).
 * Четвертый контроллер представления ссылается на другое представление с именем login.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");

    }
}
