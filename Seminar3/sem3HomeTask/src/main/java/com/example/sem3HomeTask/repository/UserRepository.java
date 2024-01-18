package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс репозитория для управления сущностями пользователя.
 * Этот класс предоставляет методы для взаимодействия с коллекцией объектов User.
 */
@Component
public class UserRepository {

    /**
     * Список для хранения сущностей пользователей.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Получает список сущностей пользователей.
     *
     * @return Список сущностей пользователей.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Устанавливает список сущностей пользователей.
     *
     * @param users Список сущностей пользователей для установки.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }



}
