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
        List<Task> tasks = taskServise.getAllTasks();
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
        taskServise.createTask(task);
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
        Task task = taskServise.getTaskById(id);
        task.setTitle(editTask.getTitle());
        task.setDescription(editTask.getDescription());
        task.setCompleted(editTask.isCompleted());
        task.setCompletedAt(editTask.getCompletedAt());
        taskServise.updateTask(task);
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
        taskServise.deleteTask(id);
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
        taskServise.completeTask(id);
        return "redirect:/tasks";
    }
}
