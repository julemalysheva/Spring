package com.example.sem3hometaskh2.services;

import com.example.sem3hometaskh2.domain.User;
import com.example.sem3hometaskh2.repository.UserRepository;
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
     * @param repository          Репозиторий пользователей для внедрения.
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
     * @return Список пользователей, отсортированный по возрасту.
     */
    public List<User> sortUsersByAge() {
        notificationService.sendNotification("Выполнена операция - Сортировка по возрасту");
        return repository.getUsers()
                .stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Фильтрует список пользователей по возрасту, оставляя только тех, кто старше заданного возраста.
     *
     * @param age   Заданный возраст для фильтрации.
     * @return Список пользователей, отфильтрованный по возрасту.
     */
    public List<User> filterUsersByAge(int age) {
        notificationService.sendNotification("Выполнена операция - Фильтр по возрасту: старше " + age);
        return repository.getUsers()
                .stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Рассчитывает средний возраст пользователей в списке.
     *
     * @return Средний возраст пользователей.
     */
    public double calculateAverageAge() {
        notificationService.sendNotification("Выполнена операция - Расчет среднего возраста");
        List<User> users = repository.getUsers();
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
    public void saveUser(User user) {
        repository.save(user);
    }
}
