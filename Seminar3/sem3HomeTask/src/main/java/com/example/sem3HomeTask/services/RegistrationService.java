package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для регистрации новых пользователей.
 * Этот класс предоставляет методы для обработки процесса регистрации и взаимодействия с другими сервисами.
 */
@Service
public class RegistrationService {

    @Autowired
    private DataProcessingService dataProcessingService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Получает сервис для обработки данных.
     *
     * @return Сервис для обработки данных.
     */
    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    /**
     * Метод для обработки процесса регистрации нового пользователя.
     * Создает нового пользователя через UserService, добавляет его в список через DataProcessingService,
     * и отправляет уведомление через NotificationService об успешной операции.
     *
     * @param name  Имя нового пользователя.
     * @param age   Возраст нового пользователя.
     * @param email Электронная почта нового пользователя.
     * @return Объект User, представляющий созданного пользователя.
     */
    public User processRegistration(String name, int age, String email) {
        User newUser = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(newUser);
        notificationService.sendNotification("Выполнена операция - Пользователь: " + newUser.getName() + "" +
                " добавлен в список");

        return newUser;
    }
}
