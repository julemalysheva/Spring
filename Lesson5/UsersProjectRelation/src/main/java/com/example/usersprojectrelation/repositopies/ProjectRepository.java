package com.example.usersprojectrelation.repositopies;

import com.example.usersprojectrelation.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория для сущности "Проект"
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
