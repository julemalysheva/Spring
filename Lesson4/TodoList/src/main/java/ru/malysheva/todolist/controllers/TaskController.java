package ru.malysheva.todolist.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malysheva.todolist.model.Task;
import ru.malysheva.todolist.services.TaskServise;

import java.util.List;

/**
 * Контроллер для управления веб-интерфейсом задач в приложении TodoList.
 */
@Controller
@Log
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private TaskServise taskServise;

    /**
     * Метод для отображения списка задач.
     *
     * @param model Объект Model для передачи данных в представление.
     * @return Имя представления для отображения списка задач.
     */
    @GetMapping
    public String showTasks(Model model) {
        log.info("Запрос на отображение списка задач.");
        List<Task> tasks = taskServise.getAllTasks();
        log.info(String.format("Получено %d задач из базы данных.", tasks.size()));
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    /**
     * Метод для отображения формы создания новой задачи.
     *
     * @param model Объект Model для передачи данных в представление.
     * @return Имя представления для создания новой задачи.
     */
    @GetMapping("/new")
    public String createTask(Model model) {
        log.info("Запрос на отображение формы создания новой задачи.");
        model.addAttribute("task", new Task());
        return "new-task";
    }

    /**
     * Метод для обработки запроса на создание новой задачи.
     *
     * @param task Задача для создания.
     * @return Редирект на страницу списка задач после создания.
     */
    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        log.info(String.format("Запрос на создание новой задачи с заголовком: '%s'", task.getTitle()));
        taskServise.createTask(task);
        log.info(String.format("Создана новая задача с заголовком: '%s'", task.getTitle()));
        return "redirect:/tasks";
    }

    /**
     * Метод для отображения формы редактирования задачи.
     *
     * @param id    Идентификатор задачи для редактирования.
     * @param model Объект Model для передачи данных в представление.
     * @return Имя представления для редактирования задачи.
     */
    @GetMapping("/{id}/edit")
    public String showEditTaskForm(@PathVariable("id") Long id, Model model) {
        log.info(String.format("Запрос на отображение формы редактирования задачи с ID: '%d'", id));
        Task task = taskServise.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

    /**
     * Метод для обработки запроса на редактирование задачи.
     *
     * @param id      Идентификатор задачи для редактирования.
     * @param editTask Задача с обновленными данными.
     * @return Редирект на страницу списка задач после редактирования.
     */
    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable("id") Long id, @ModelAttribute Task editTask) {
        log.info(String.format("Запрос на редактирование задачи с ID: '%d'", id));
        Task task = taskServise.getTaskById(id);
        task.setTitle(editTask.getTitle());
        task.setDescription(editTask.getDescription());
        task.setCompleted(editTask.isCompleted());
        task.setCompletedAt(editTask.getCompletedAt());
        taskServise.updateTask(task);
        log.info(String.format("Задача с ID: '%d' успешно отредактирована.", id));
        return "redirect:/tasks";
    }

    /**
     * Метод для обработки запроса на удаление задачи.
     *
     * @param id Идентификатор задачи для удаления.
     * @return Редирект на страницу списка задач после удаления.
     */
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") Long id) {
        log.info(String.format("Запрос на удаление задачи с ID: '%d'", id));
        taskServise.deleteTask(id);
        log.info(String.format("Задача с ID: '%d' успешно удалена.", id));
        return "redirect:/tasks";
    }

    /**
     * Метод для обработки запроса на отметку задачи как выполненной.
     *
     * @param id Идентификатор задачи для отметки как выполненной.
     * @return Редирект на страницу списка задач после отметки.
     */
    @PostMapping("/{id}/complete")
    public String completeTask(@PathVariable("id") Long id) {
        log.info(String.format("Запрос на отметку задачи с ID: '%d' как выполненной.", id));
        taskServise.completeTask(id);
        log.info(String.format("Задача с ID: '%d' успешно отмечена как выполненная.", id));
        return "redirect:/tasks";
    }
}
