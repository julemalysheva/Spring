package com.example.sem3hometaskh2.controllers;

import com.example.sem3hometaskh2.domain.User;
import com.example.sem3hometaskh2.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с операциями по обработке данных о пользователях.
 * Этот класс предоставляет методы для получения списка задач и выполнения операций с пользователями.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service;

    /**
     * Обработчик GET-запроса для получения списка задач.
     *
     * @return Список задач.
     */
    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    /**
     * Обработчик GET-запроса для сортировки списка пользователей по возрасту.
     *
     * @return Список пользователей, отсортированный по возрасту.
     */
    @GetMapping("/sort")//localhost:8080/tasks/sort
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge();
    }

    /**
     * Обработчик GET-запроса для фильтрации списка пользователей по возрасту.
     *
     * @param age Возраст для фильтрации пользователей.
     * @return Список пользователей, отфильтрованный по возрасту.
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable("age") int age) {
        return service.filterUsersByAge(age);
    }

    /**
     * Обработчик GET-запроса для расчета среднего возраста в списке пользователей.
     *
     * @return Средний возраст пользователей.
     */
    @GetMapping("/calc")
    public double calculateAverageAge() {
        return service.calculateAverageAge();
    }

}
