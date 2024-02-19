package com.example.todolist.service.strategy;

import com.example.todolist.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Стратегия сортировки задач по статусу.
 */
public class SortByStatusStrategy implements TaskSortingStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }
}
