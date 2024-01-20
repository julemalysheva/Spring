package ru.malysheva.todolist.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malysheva.todolist.model.Task;
import ru.malysheva.todolist.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервисный класс для управления задачами.
 * Обеспечивает взаимодействие между контроллерами и репозиторием для выполнения операций с задачами.
 */
@Service
@AllArgsConstructor
public class TaskServise {
    private TaskRepository taskRepository;

    /**
     * Метод для получения списка всех задач.
     *
     * @return Список задач.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Метод для получения задачи по её идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Метод для создания новой задачи.
     *
     * @param task Задача для создания.
     */
    public void createTask(Task task) {
        task.setCreatedAt(LocalDate.now());
        task.setCompleted(false);
        task.setCompletedAt(null);
        taskRepository.save(task);
    }

    /**
     * Метод для отметки задачи как выполненной.
     *
     * @param id Идентификатор задачи.
     */
    public void completeTask(Long id) {
        Task task = taskRepository.findById(id);
        if (task != null) {
            task.setCompleted(true);
            task.setCompletedAt(LocalDate.now());
            taskRepository.update(task);
        }
    }

    /**
     * Метод для обновления задачи.
     *
     * @param task Задача для обновления.
     */
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    /**
     * Метод для удаления задачи по её идентификатору.
     *
     * @param id Идентификатор задачи.
     */
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
