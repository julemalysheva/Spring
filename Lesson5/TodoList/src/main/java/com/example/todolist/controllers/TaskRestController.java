package com.example.todolist.controllers;

import com.example.todolist.model.Task;
import com.example.todolist.model.TaskStatus;
import com.example.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс TaskRestController представляет REST-контроллер для взаимодействия с задачами в списке дел.
 */
@RestController
@RequestMapping("/api/tasks") //http://localhost:8080/api/tasks
@Slf4j
@AllArgsConstructor
public class TaskRestController {
    private final TaskService taskService;

    /**
     * Метод для добавления новой задачи.
     * @param task Новая задача для добавления.
     * @return Сохраненная задача.
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        log.info("Received add task request: {}", task.getDescription());
        return taskService.addTask(task);
    }

    /**
     * Метод для получения всех задач из списка дел.
     * @return Список всех задач.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        log.info("Received get all tasks request");
        return taskService.getAllTasks();
    }

    /**
     * Метод для получения задач по указанному статусу.
     * @param status Статус задач, по которому осуществляется фильтрация.
     * @return Список задач с указанным статусом.
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        log.info("Received get tasks by status request: ", status);
        return taskService.getTasksByStatus(status);
    }

    /**
     * Метод для обновления статуса задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param status Новый статус задачи.
     * @return Обновленная задача.
     */
    @PutMapping("/{id}/status/{status}")
    public Task updateTaskStatus(@PathVariable Long id, @PathVariable TaskStatus status) {
        log.info("Received update task status request: id = {}, status = {}", id, status);
        return taskService.updateTaskStatus(id, status);
    }

    /**
     * Метод для обновления информации о задаче по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param updateTask Обновленные данные задачи.
     * @return Обновленная задача.
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updateTask) {
        log.info("Received update task request: id = {}, task = {}", id, updateTask);
        return taskService.updateTask(id, updateTask);
    }

    /**
     * Метод для удаления задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Received delete task request: id = {}", id);
        taskService.deleteTask(id);
    }

    /**
     * Метод для получения задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     */
    @GetMapping("/{id}")
    public Task getTasksById(@PathVariable Long id) {
        log.info("Received get task request: id = {}", id);
        return taskService.getTaskById(id);
    }
}
