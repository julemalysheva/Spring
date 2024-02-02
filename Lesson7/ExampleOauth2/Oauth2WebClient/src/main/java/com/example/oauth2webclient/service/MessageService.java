package com.example.oauth2webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Сервис MessageService предоставляет методы для взаимодействия с ресурсами сообщений.
 */
@Service
public class MessageService {

    @Autowired
    private RestTemplate template;
    @Autowired
    private HttpHeaders headers;

    /**
     * Базовый URL сервера ресурсов.
     */
    private static final String RESOURCE_API_BASE_URL = "http://localhost:8080";

    /**
     * Получает все сообщения с сервера ресурсов.
     *
     * @param token токен доступа
     * @return строка с сообщениями
     */
    public String getAllMessages(String token) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(
                RESOURCE_API_BASE_URL + "/message", HttpMethod.GET, entity, String.class);

        return response.getBody();
    }


    /**
     * Создает новое сообщение на сервере ресурсов.
     *
     * @param message текст сообщения
     * @param token   токен доступа
     * @return строка с созданным сообщением
     * @throws RuntimeException если не удалось создать сообщение
     */
    public String createMessage(String message, String token) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = template.exchange(
                RESOURCE_API_BASE_URL + "/message", HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to create message");
        }
    }



}

