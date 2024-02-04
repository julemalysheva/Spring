package com.example.oauth2webclient.controller;

import com.example.oauth2webclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Контроллер для обработки сообщений и получения картинки.
 */
@Controller
public class ClientController {

    /**
     * Базовый URL клиентского сервера http://localhost:8090
     */
    @Autowired
    private ClientService clientService;

    /**
     * Обрабатывает GET-запрос для получения картинки.
     *
     * @param model            объект Model для передачи данных в представление
     * @param authorizedClient объект OAuth2AuthorizedClient для получения информации о клиенте
     * @return строку, представляющую имя представления для отображения картинки
     */
    @GetMapping("/image")
    public String getImageBase64(Model model,
                                 @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        // Получение токена доступа
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        // Вызов сервисного метода с передачей токена
        String base64Image = clientService.getImageBase64(accessToken);

        model.addAttribute("base64Image", base64Image);
        return "image-base64";
    }

    /**
     * Обрабатывает GET-запрос для получения всех сообщений.
     *
     * @param model            объект Model для передачи данных в представление
     * @param authorizedClient объект OAuth2AuthorizedClient для получения информации о клиенте
     * @return строку, представляющую имя представления
     */
    @GetMapping("/messages") // http://localhost:8090/messages
    public String getAllMessages(Model model,
                                 @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        // Получение токена доступа
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        // Вызов сервисного метода с передачей токена
        String messages = clientService.getAllMessages(accessToken);

        model.addAttribute("messages", messages);
        return "messages";
    }

    /**
     * Обрабатывает GET-запрос для получения формы создания нового сообщения.
     *
     * @return строку, представляющую имя представления для формы нового сообщения
     */
    @GetMapping("/messages/new")
    public String getNewMessageForm() {
        return "new-message";
    }

    /**
     * Обрабатывает POST-запрос для создания нового сообщения.
     *
     * @param message            текст нового сообщения
     * @param model              объект Model для передачи данных в представление
     * @param redirectAttributes объект RedirectAttributes для добавления атрибутов к перенаправлению
     * @param authorizedClient объект OAuth2AuthorizedClient для получения информации о клиенте
     * @return строку, представляющую имя представления для нового сообщения
     */
    @PostMapping("/messages")
    public String createMessage(@RequestParam("message") String message, Model model,
                                RedirectAttributes redirectAttributes,
                                @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        // Получение токена доступа
        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        try {
            String newMessage = clientService.createMessage(message, accessToken);
            model.addAttribute("message", newMessage);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create message");
            return "redirect:/messages/new";
        }

        return "new-message";


    }
}



