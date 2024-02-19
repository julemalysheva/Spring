package com.example.todolist.gateway;

import com.example.todolist.model.Task;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Интерфейс для записи данных в файл через Spring Integration.
 */
@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface FileGateway {

    /**
     * Метод для записи данных в файл.
     * @param filename Имя файла.
     * @param data Данные для записи.
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, Task data);
}
