package com.example.todolist.service.strategy;

import com.example.todolist.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Стратегия сортировки задач по описанию.
 */
public class SortByDescriptionStrategy implements TaskSortingStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDescription))
                .collect(Collectors.toList());
    }
}
