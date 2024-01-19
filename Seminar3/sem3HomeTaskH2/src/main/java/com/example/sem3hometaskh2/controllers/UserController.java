package com.example.sem3hometaskh2.controllers;

import com.example.sem3hometaskh2.domain.User;
import com.example.sem3hometaskh2.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с пользователями.
 * Этот класс предоставляет методы для получения списка пользователей, добавления пользователей из тела запроса
 * и регистрации нового пользователя из параметров запроса.
 */
@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {

    @Autowired
    private RegistrationService service;

    /**
     * Обработчик GET-запроса для получения списка пользователей.
     *
     * @return Список пользователей.
     */
    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }

    /**
     * Обработчик POST-запроса для добавления пользователя из тела запроса.
     *
     * @param user Объект User, представляющий пользователя для добавления.
     * @return Строка с сообщением об успешном добавлении пользователя.
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.getDataProcessingService().saveUser(user);
        return "User added from body!";
    }

    /**
     * Обработчик POST-запроса для регистрации нового пользователя из параметров запроса.
     *
     * @param name  Имя нового пользователя.
     * @param age   Возраст нового пользователя.
     * @param email Электронная почта нового пользователя.
     * @return Строка с сообщением об успешном добавлении пользователя.
     */
    @PostMapping
    public String userAddFromParam(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String email) {
        service.processRegistration(name, age, email);
        return "User added from param!";
    }

}
