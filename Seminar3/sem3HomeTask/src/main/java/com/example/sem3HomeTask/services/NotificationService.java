package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для управления уведомлениями.
 * Этот класс предоставляет методы для уведомления пользователей и отправки произвольных уведомлений.
 */
@Service
public class NotificationService {

    /**
     * Уведомляет пользователя о создании нового пользователя.
     *
     * @param user Объект User, представляющий созданного пользователя.
     */
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    /**
     * Отправляет произвольное уведомление.
     *
     * @param s Строка, представляющая текст уведомления.
     */
    public void sendNotification(String s) {
        System.out.println(s);
    }
}
