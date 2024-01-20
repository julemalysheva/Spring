CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    createdAt DATE NOT NULL,
    completed BOOLEAN NOT NULL,
    completedAt DATE
    );

INSERT INTO tasks (title, description, createdAt, completed, completedAt)
VALUES ('Задача 1', 'Описание задачи 1', CURDATE(), 0, NULL);

INSERT INTO tasks (title, description, createdAt, completed, completedAt)
VALUES ('Задача 2', 'Описание задачи 2', CURDATE(), 0, NULL);

INSERT INTO tasks (title, description, createdAt, completed, completedAt)
VALUES ('Задача 3', 'Описание задачи 3', CURDATE(), 1, CURDATE());

INSERT INTO tasks (title, description, createdAt, completed, completedAt)
VALUES ('Задача 4', 'Описание задачи 4', CURDATE(), 1, CURDATE());