package com.example.securitytask01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация контроллеров представлений для обработки URL-адресов
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Конфигурация контроллеров представлений для URL-адресов в веб-приложении.
     *
     * Этот метод добавляет ассоциации между URL-адресами и представлениями,
     * устанавливая соответствующие представления для каждого URL-адреса.
     * Это облегчает навигацию и управление отображением страниц в приложении.
     *
     * @param registry Реестр контроллеров представлений для конфигурации.
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/public-data").setViewName("public-data");
        registry.addViewController("/").setViewName("public-data");
        registry.addViewController("/private-data").setViewName("private-data");
        registry.addViewController("/login").setViewName("login");

    }
}
