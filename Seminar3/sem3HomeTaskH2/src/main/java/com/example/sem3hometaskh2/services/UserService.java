package com.example.sem3hometaskh2.services;

import com.example.sem3hometaskh2.domain.User;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для управления операциями, связанными с пользователями.
 * Этот класс предоставляет функциональность по созданию и управлению пользователями.
 */
@Service
public class UserService {

   // @Autowired
    private NotificationService notificationService;

    /**
     * Конструктор для внедрения зависимости от службы уведомлений.
     *
     * @param notificationService Служба уведомлений для внедрения.
     */
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Создает нового пользователя с заданными параметрами.
     * Отправляет уведомление о создании нового пользователя через службу уведомлений.
     *
     * @param name  Имя пользователя.
     * @param age   Возраст пользователя.
     * @param email Электронная почта пользователя.
     * @return Объект User, представляющий созданного пользователя.
     */
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);

        return user;
    }
}
