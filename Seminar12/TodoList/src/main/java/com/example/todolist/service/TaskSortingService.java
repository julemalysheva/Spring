package com.example.todolist.service;

import com.example.todolist.model.Task;
import com.example.todolist.service.strategy.SortByCreationDateStrategy;
import com.example.todolist.service.strategy.SortByDescriptionStrategy;
import com.example.todolist.service.strategy.SortByStatusStrategy;
import com.example.todolist.service.strategy.TaskSortingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для сортировки задач с использованием стратегий.
 */
@Service
public class TaskSortingService {
    private final Map<String, TaskSortingStrategy> strategies;

    @Autowired
    private TaskService taskService;

    /**
     * Конструктор для инициализации стратегий сортировки задач.
     */
    public TaskSortingService() {
        this.strategies = new HashMap<>();
        strategies.put("status", new SortByStatusStrategy());
        strategies.put("creationDate", new SortByCreationDateStrategy());
        strategies.put("description", new SortByDescriptionStrategy());
    }

    /**
     * Получает отсортированный список задач в соответствии с выбранной стратегией.
     * @param sortBy Ключ стратегии сортировки.
     * @return Отсортированный список задач.
     */
    public List<Task> getSortedTasks(String sortBy) {
        List<Task> tasks = taskService.getAllTasks();
        if (sortBy != null && strategies.containsKey(sortBy)) {
            return strategies.get(sortBy).sort(tasks);
        } else {
            return tasks;
        }
    }
}

