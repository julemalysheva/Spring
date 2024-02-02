package com.example.oauth2resourceserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности для OAuth2 Resource Server.
 *
 */
@Configuration
@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration {

	@Value("${spring.security.oauth2.resource-server.jwt.jwk-set-uri}")
	String jwkSetUri;

	/**
	 * Конфигурирует фильтры и правила безопасности для OAuth2 Resource Server.
	 *
	 * @param http объект HttpSecurity для конфигурации безопасности
	 * @return SecurityFilterChain для Resource Server
	 * @throws Exception Если произошла ошибка во время конфигурации
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
//						.requestMatchers(HttpMethod.GET, "/message/**").hasAuthority("SCOPE_message:read")
//						.requestMatchers(HttpMethod.POST, "/message/**").hasAuthority("SCOPE_message:write")
						.anyRequest().authenticated()
				)
				.oauth2ResourceServer((oauth2) -> oauth2
						.jwt(withDefaults()));
		return http.build();
	}

	/**
	 * Создает бин JwtDecoder для декодирования JWT.
	 *
	 * @return JwtDecoder для декодирования JWT
	 */
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
	}

}
