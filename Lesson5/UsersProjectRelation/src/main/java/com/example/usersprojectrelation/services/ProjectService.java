package com.example.usersprojectrelation.services;

import com.example.usersprojectrelation.domain.Project;
import com.example.usersprojectrelation.repositopies.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Сервисный класс для обработки операций с проектами
 */
@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;


    /**
     * Метод для добавления проекта
     * @param project Проект для добавления
     * @return Добавленный проект
     */
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
}
