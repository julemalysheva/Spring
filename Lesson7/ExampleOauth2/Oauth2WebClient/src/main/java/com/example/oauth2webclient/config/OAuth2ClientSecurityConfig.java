package com.example.oauth2webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности для OAuth2 клиентского сервера.
 *
 */
@Configuration
@EnableWebSecurity
public class OAuth2ClientSecurityConfig {

    /**
     * Конфигурирует фильтры и правила безопасности для OAuth2 клиентского сервера.
     *
     * @param http объект HttpSecurity для конфигурации безопасности
     * @return SecurityFilterChain для клиентского сервера
     * @throws Exception Если произошла ошибка во время конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
//                .formLogin(withDefaults())
                .oauth2Login(withDefaults())
        ;
        return http.build();
    }
}
