
package com.example.oauth2resourceserver;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер ресурсов OAuth.
 */
@RestController
public class OAuth2ResourceServerController {

	/**
	 * Обработка запроса на главную страницу.
	 *
	 * @param jwt JWT токен пользователя
	 * @return Приветственное сообщение с именем пользователя
	 */
	@GetMapping("/")
	public String index(@AuthenticationPrincipal Jwt jwt) {
		return String.format("Hello, %s!", jwt.getSubject());
	}

	/**
	 * Обработка GET запроса на эндпоинт "/message".
	 *
	 * @return ResponseEntity с "секретным" сообщением
	 */
	@GetMapping("/message")
	public ResponseEntity<String> message() {
		return ResponseEntity.ok("secret message");
	}

	/**
	 * Обработка POST запроса на эндпоинт "/message".
	 *
	 * @param message Тело запроса с сообщением
	 * @return ResponseEntity с подтверждением создания сообщения и его содержимым
	 */
	@PostMapping("/message")
	public ResponseEntity<String> createMessage(@RequestBody String message) {
		return ResponseEntity.ok(String.format("Message was created. Content: %s", message));
	}

}
