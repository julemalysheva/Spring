package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный класс для обработки данных о пользователях.
 * Этот класс предоставляет методы для сортировки, фильтрации и расчета среднего по возрасту пользователей.
 */
@Service
public class DataProcessingService {

    private UserRepository repository;
    private NotificationService notificationService;

    /**
     * Конструктор для внедрения зависимостей от репозитория пользователей и службы уведомлений.
     *
     * @param repository           Репозиторий пользователей для внедрения.
     * @param notificationService Служба уведомлений для внедрения.
     */
    public DataProcessingService(UserRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    /**
     * Получает репозиторий пользователей.
     *
     * @return Репозиторий пользователей.
     */
    public UserRepository getRepository() {
        return repository;
    }

    /**
     * Сортирует список пользователей по возрасту.
     *
     * @param users Список пользователей для сортировки.
     * @return Список пользователей, отсортированный по возрасту.
     */
    public List<User> sortUsersByAge(List<User> users) {
        notificationService.sendNotification("Выполнена операция - Сортировка по возрасту");
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Фильтрует список пользователей по возрасту, оставляя только тех, кто старше заданного возраста.
     *
     * @param users Список пользователей для фильтрации.
     * @param age   Заданный возраст для фильтрации.
     * @return Список пользователей, отфильтрованный по возрасту.
     */
    public List<User> filterUsersByAge(List<User> users, int age) {
        notificationService.sendNotification("Выполнена операция - Фильтр по возрасту: старше " + age);
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Рассчитывает средний возраст пользователей в списке.
     *
     * @param users Список пользователей для расчета.
     * @return Средний возраст пользователей.
     */
    public double calculateAverageAge(List<User> users) {
        notificationService.sendNotification("Выполнена операция - Расчет среднего возраста");
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    /**
     * Добавляет пользователя в список репозитория.
     *
     * @param user Пользователь для добавления.
     */
    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }
}
