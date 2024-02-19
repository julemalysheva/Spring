package com.example.todolist.config;

import com.example.todolist.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Конфигурационный класс для настройки интеграции Spring.
 */

@Configuration
@EnableIntegration
public class IntegrationConfig {

    /**
     * Создает канал для передачи текстовых сообщений от пользователя.
     * @return Канал для входящих текстовых сообщений.
     */
    @Bean
    public MessageChannel textInputChannel() {
        return new DirectChannel();
    }

    /**
     * Создает канал для передачи текстовых сообщений для записи в файл.
     * @return Канал для исходящих текстовых сообщений.
     */
    @Bean
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    /**
     * Основной преобразователь, который преобразует объект Task в строку JSON.
     * @param objectMapper Объект ObjectMapper для преобразования объектов в JSON.
     * @return Преобразователь, выполняющий преобразование объекта Task в строку JSON.
     */
    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<Task, String> mainTransformer(ObjectMapper objectMapper) {
        return task -> {
            try {
                return objectMapper.writeValueAsString(task);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * Обработчик сообщений, который записывает текстовые сообщения в файл.
     * @return Обработчик сообщений для записи в файл.
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(
                "D:/GB_JAVA/Spring/Projects/Seminar12/TodoList/files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}

