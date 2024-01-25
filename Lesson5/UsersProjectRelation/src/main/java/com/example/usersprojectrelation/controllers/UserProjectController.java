package com.example.usersprojectrelation.controllers;

import com.example.usersprojectrelation.domain.Project;
import com.example.usersprojectrelation.domain.User;
import com.example.usersprojectrelation.services.ProjectService;
import com.example.usersprojectrelation.services.UserProjectService;
import com.example.usersprojectrelation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для обработки HTTP-запросов по управлению пользователями и проектами
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserProjectController {
    private final UserProjectService service;

    //добавила методы создания пользователя и проекта
    private final UserService userService;
    private final ProjectService projectService;

    /**
     * Метод для получения списка пользователей, связанных с определенным проектом.
     *
     * @param projectId Идентификатор проекта.
     * @return ResponseEntity с данными о пользователях и статусом ответа.
     */
    @GetMapping("/users/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> users = service.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

    /**
     * Метод для получения списка проектов, связанных с определенным пользователем.
     *
     * @param userId Идентификатор пользователя.
     * @return ResponseEntity с данными о проектах и статусом ответа.
     */
    @GetMapping("/projects/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = service.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    /**
     * Метод для добавления пользователя к проекту.
     *
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     * @return ResponseEntity с результатом добавления и статусом ответа.
     */
    @PostMapping("/add-user/{userId}/to-project/{projectId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long userId, @PathVariable Long projectId) {
        service.addUserToProject(userId, projectId);
        //        return ResponseEntity.ok("User added to project successfully");
        return ResponseEntity.ok().build();
    }

    /**
     * Метод для удаления пользователя из проекта.
     *
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     * @return ResponseEntity с результатом удаления и статусом ответа.
     */
    @PostMapping("/remove-user/{userId}/from-project/{projectId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        service.removeUserFromProject(userId, projectId);
        //        return ResponseEntity.ok("User removed from project successfully");
        return ResponseEntity.ok().build();
    }


    /**
     * Метод, обрабатывающий POST-запрос для добавления пользователя
     * @param user Пользователь для добавления
     * @return Ответ со статусом операции
     */
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Метод, обрабатывающий POST-запрос для добавления проекта
     * @param project Проект для добавления
     * @return Ответ со статусом операции
     */
    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project newProject = projectService.addProject(project);
        return ResponseEntity.ok(newProject);
    }
}
