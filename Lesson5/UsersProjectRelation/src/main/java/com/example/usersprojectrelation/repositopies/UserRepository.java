package com.example.usersprojectrelation.repositopies;

import com.example.usersprojectrelation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория для сущности "Пользователь"
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
