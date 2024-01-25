package com.example.usersprojectrelation.services;

import com.example.usersprojectrelation.domain.User;
import com.example.usersprojectrelation.repositopies.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для обработки операций с пользователями
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Метод для добавления пользователя
     * @param user Пользователь для добавления
     * @return Добавленный пользователь
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
