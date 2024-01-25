package com.example.usersprojectrelation.services;

import com.example.usersprojectrelation.domain.Project;
import com.example.usersprojectrelation.domain.User;
import com.example.usersprojectrelation.domain.UsersProject;
import com.example.usersprojectrelation.repositopies.ProjectRepository;
import com.example.usersprojectrelation.repositopies.UserRepository;
import com.example.usersprojectrelation.repositopies.UsersProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс для работы с сущностью "Пользователи проекта"
 */
@Service
@AllArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UsersProjectRepository usersProjectRepository;

    /**
     * Метод для получения списка пользователей, связанных с определенным проектом.
     *
     * @param projectId Идентификатор проекта.
     * @return Список пользователей, связанных с проектом.
     */
    public List<User> getUsersByProjectId(Long projectId) {
        List<User> users = new ArrayList<>();
        List<UsersProject> userProjects = usersProjectRepository.findByProjectId(projectId);

        for (UsersProject userProject : userProjects) {
            Optional<User> userOptional = userRepository.findById(userProject.getUserId());
            userOptional.ifPresent(users::add);
        }

        return users;
    }

    /**
     * Метод для получения списка проектов, связанных с определенным пользователем.
     *
     * @param userId Идентификатор пользователя.
     * @return Список проектов, связанных с пользователем.
     */
    public List<Project> getProjectsByUserId(Long userId) {
        List<Project> projects = new ArrayList<>();
        List<UsersProject> userProjects = usersProjectRepository.findByUserId(userId);

        for (UsersProject userProject : userProjects) {
            Optional<Project> projectOptional = projectRepository.findById(userProject.getProjectId());
            projectOptional.ifPresent(projects::add);
        }

        return projects;
    }

    /**
     * Метод для добавления пользователя к проекту.
     *
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    public void addUserToProject(Long userId, Long projectId) {
        UsersProject userProject = new UsersProject();
        userProject.setUserId(userId);
        userProject.setProjectId(projectId);

        usersProjectRepository.save(userProject);
    }

    /**
     * Метод для удаления пользователя из проекта.
     *
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    @Transactional
    public void removeUserFromProject(Long userId, Long projectId) {
        usersProjectRepository.deleteByUserIdAndProjectId(userId, projectId);
    }

}
