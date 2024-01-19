package com.example.sem3hometaskh2.repository;

import com.example.sem3hometaskh2.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-репозиторий для взаимодействия с базой данных в контексте сущности "пользователь".
 * Использует JdbcTemplate для выполнения SQL-запросов.
 */
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    /**
     * Конструктор класса UserRepository.
     *
     * @param jdbc Объект JdbcTemplate, внедряемый Spring Framework для взаимодействия с базой данных.
     */
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Получает всех пользователей из базы данных.
     *
     * @return Список пользователей
     */
    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, getUserRowMapper());
    }

    /**
     * Получает RowMapper для сопоставления данных из ResultSet с объектом User.
     *
     * @return RowMapper для User
     */
    private RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> mapUser(resultSet);
    }

    /**
     * Сопоставляет данные из ResultSet с объектом User.
     *
     * @param resultSet ResultSet с данными пользователя
     * @return Объект User с данными из ResultSet
     * @throws SQLException
     */
    private User mapUser(ResultSet resultSet) throws SQLException {
        User rowObject = new User();
        rowObject.setId(resultSet.getInt("id"));
        rowObject.setName(resultSet.getString("name"));
        rowObject.setAge(resultSet.getInt("age"));
        rowObject.setEmail(resultSet.getString("email"));
        return rowObject;
    }

    /**
     * Сохраняет нового пользователя в базе данных.
     *
     * @param user Пользователь для сохранения
     * @return Сохраненный пользователь
     */
    public User save(User user) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        return user;
    }
}
