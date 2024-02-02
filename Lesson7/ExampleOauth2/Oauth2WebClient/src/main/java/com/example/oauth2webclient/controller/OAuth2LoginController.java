package com.example.oauth2webclient.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс OAuth2LoginController отвечает за обработку запросов связанных с OAuth2 аутентификацией и входом пользователя.
 *
 */
@Controller
public class OAuth2LoginController {


	/**
	 * Обрабатывает GET-запрос для отображения основной страницы после OAuth2 аутентификации.
	 *
	 * @param model             объект Model для передачи данных в представление
	 * @param authorizedClient объект OAuth2AuthorizedClient для получения информации о клиенте
	 * @param oauth2User        объект OAuth2User для получения информации о пользователе
	 * @return строку, представляющую имя представления
	 */
	@GetMapping("/")
	public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		model.addAttribute("userAttributes", oauth2User.getAttributes());
		return "index";
	}



}
