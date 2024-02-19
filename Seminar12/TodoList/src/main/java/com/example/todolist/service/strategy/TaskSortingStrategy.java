package com.example.todolist.service.strategy;

import com.example.todolist.model.Task;

import java.util.List;

/**
 * Интерфейс стратегии сортировки задач.
 */

public interface TaskSortingStrategy {

    /**
     * Метод для сортировки списка задач в соответствии с выбранной стратегией.
     * @param tasks Список задач для сортировки.
     * @return Отсортированный список задач.
     */
    List<Task> sort(List<Task> tasks);
}