package ru.malysheva.todolist.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.malysheva.todolist.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Реализация интерфейса RowMapper для маппинга строк из ResultSet в объект Task.
 * Используется при выполнении SQL-запросов с использованием JdbcTemplate.
 */

public class TaskRowMapper implements RowMapper<Task> {
    /**
     * Метод для маппинга строки ResultSet в объект Task.
     *
     * @param rs     ResultSet, представляющий строку из результата SQL-запроса.
     * @param rowNum номер текущей строки в результате запроса.
     * @return объект Task, представляющий собой маппированную задачу.
     * @throws SQLException если произошла ошибка при работе с ResultSet.
     */
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setCreatedAt(rs.getObject("createdAt", LocalDate.class));
        task.setCompletedAt(rs.getObject("completedAt", LocalDate.class));
        task.setCompleted(rs.getBoolean("completed"));

        return task;
    }
}
