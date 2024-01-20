package ru.malysheva.todolist.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.malysheva.todolist.config.DatabaseConfig;
import ru.malysheva.todolist.model.Task;

import java.util.List;

/**
 * Репозиторий для взаимодействия с базой данных для сущности Task.
 * Используется для выполнения CRUD-операций с задачами.
 */
@Repository
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;

    private String findAllTasksSql;
    private String findTaskByIdSql;
    private String saveTaskSql;
    private String updateTaskSql;
    private String deleteTaskSql;

    /**
     * Конструктор класса. Инициализирует JdbcTemplate и SQL-запросы из объекта DatabaseConfig.
     *
     * @param jdbcTemplate   Объект JdbcTemplate для выполнения SQL-запросов.
     * @param databaseConfig Конфигурационный объект для хранения SQL-запросов.
     */
    public TaskRepository(JdbcTemplate jdbcTemplate, DatabaseConfig databaseConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.findAllTasksSql = databaseConfig.getFindAllTasksSql();
        this.findTaskByIdSql = databaseConfig.getFindTaskByIdSql();
        this.saveTaskSql = databaseConfig.getSaveTaskSql();
        this.updateTaskSql = databaseConfig.getUpdateTaskSql();
        this.deleteTaskSql = databaseConfig.getDeleteTaskSql();
    }

    /**
     * Метод для получения списка всех задач из базы данных.
     *
     * @return Список задач.
     */
    public List<Task> findAll() {
        return jdbcTemplate.query(findAllTasksSql, new TaskRowMapper());
    }

    /**
     * Метод для получения задачи по её идентификатору из базы данных.
     *
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     */
    public Task findById(Long id) {
        return jdbcTemplate.queryForObject(findTaskByIdSql, new TaskRowMapper(), id);
    }

    /**
     * Метод для сохранения новой задачи в базе данных.
     *
     * @param task Задача для сохранения.
     */
    public void save(Task task) {
        jdbcTemplate.update(saveTaskSql, task.getTitle(), task.getDescription(), task.getCreatedAt(),
                task.isCompleted(), task.getCompletedAt());
    }

    /**
     * Метод для обновления существующей задачи в базе данных.
     *
     * @param task Задача для обновления.
     */
    public void update(Task task) {
        jdbcTemplate.update(updateTaskSql, task.getTitle(), task.getDescription(),
                task.isCompleted(), task.getCompletedAt(), task.getId());
    }

    /**
     * Метод для удаления задачи из базы данных по её идентификатору.
     *
     * @param id Идентификатор задачи для удаления.
     */
    public void delete(Long id) {
        jdbcTemplate.update(deleteTaskSql, id);
    }
}
