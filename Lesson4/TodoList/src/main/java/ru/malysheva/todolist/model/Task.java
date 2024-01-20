package ru.malysheva.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Класс, представляющий модель задачи.
 * Используется для хранения информации о задаче в приложении TodoList.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private boolean completed;
    private LocalDate completedAt;
}
