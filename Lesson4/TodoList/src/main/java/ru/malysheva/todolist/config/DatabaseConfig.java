package ru.malysheva.todolist.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфигурационный класс для хранения SQL-запросов к базе данных.
 * Используется для вынесения SQL-запросов из кода в настройки приложения.
 */

@Data
@ConfigurationProperties("database")
public class DatabaseConfig {
    private String findAllTasksSql;
    private String findTaskByIdSql;
    private String saveTaskSql;
    private String updateTaskSql;
    private String deleteTaskSql;
}
