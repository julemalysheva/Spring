package ru.malysheva.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.malysheva.todolist.config.DatabaseConfig;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseConfig.class)
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

}
