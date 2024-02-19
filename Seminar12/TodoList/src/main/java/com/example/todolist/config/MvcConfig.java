package com.example.todolist.config;

import com.example.todolist.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс MvcConfig предоставляет конфигурацию Spring MVC.
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
        registry.addViewController("/").setViewName("taskList");
        registry.addViewController("/login").setViewName("login");

    }

    /**
     * Регистрация LoggingInterceptor для перехвата запросов
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor());
    }

    /**
     * Метод для создания экземпляра LoggingInterceptor.
     *
     * @return LoggingInterceptor.
     */
    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
