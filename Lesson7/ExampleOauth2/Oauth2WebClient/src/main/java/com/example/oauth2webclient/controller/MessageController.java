package com.example.oauth2webclient.controller;

import com.example.oauth2webclient.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Контроллер для обработки сообщений.
 */
@Controller
public class MessageController {

    /**
     * Базовый URL клиентского сервера http://localhost:8090
     */
    @Autowired
    private MessageService messageService;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    /**
     * Обрабатывает GET-запрос для получения всех сообщений.
     *
     * @param model     объект Model для передачи данных в представление
     * @param principal объект Principal для получения информации о текущем пользователе
     * @return строку, представляющую имя представления
     */
    @GetMapping("/messages") // http://localhost:8090/messages
    public String getAllMessages(Model model, Principal principal) {
        // Получение токена доступа
        String accessToken = authorizedClientService
                .loadAuthorizedClient("login-client", principal.getName())
                .getAccessToken().getTokenValue();
        // Вызов сервисного метода с передачей токена
        String messages = messageService.getAllMessages(accessToken);

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
     * @param message              текст нового сообщения
     * @param model                объект Model для передачи данных в представление
     * @param redirectAttributes   объект RedirectAttributes для добавления атрибутов к перенаправлению
     * @param principal            объект Principal для получения информации о текущем пользователе
     * @return строку, представляющую имя представления для нового сообщения
     */
    @PostMapping("/messages")
    public String createMessage(@RequestParam("message") String message, Model model,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {
        // Получение токена доступа
        String accessToken = authorizedClientService
                .loadAuthorizedClient("login-client", principal.getName())
                .getAccessToken().getTokenValue();

        try {
            String newMessage = messageService.createMessage(message, accessToken);
            model.addAttribute("message", newMessage);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create message");
            return "redirect:/messages/new";
        }

        return "new-message";


    }
}



