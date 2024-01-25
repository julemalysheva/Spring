package com.example.usersprojectrelation.repositopies;

import com.example.usersprojectrelation.domain.UsersProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория для сущности "Пользователи проекта"
 */
@Repository
public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    List<UsersProject> findByUserId(Long userId);
    List<UsersProject> findByProjectId(Long projectId);
    void deleteByUserIdAndProjectId(Long userId, Long projectId);
}
