package com.example.demosecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Следующая конфигурация безопасности гарантирует, что секретное приветствие
 * смогут видеть только прошедшие проверку пользователи
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}

/**
 * WebSecurityConfig Класс снабжен примечанием @EnableWebSecurity, чтобы включить поддержку веб-безопасности
 * Spring Security и обеспечить интеграцию Spring MVC. Он также предоставляет два компонента для настройки
 * некоторых особенностей конфигурации веб-безопасности.:
 *
 * SecurityFilterChain Компонент определяет, какие пути URL должны быть защищены, а какие нет. В частности,
 * пути / и /home настроены так, чтобы не требовать никакой аутентификации. Все остальные пути должны быть
 * аутентифицированы.
 *
 * Когда пользователь успешно входит в систему, он перенаправляется на ранее запрошенную страницу,
 * которая требовала аутентификации. Существует пользовательская /login страница (которая указывается loginPage()),
 * и ее разрешено просматривать всем.
 *
 * UserDetailsService Компонент настраивает пользовательское хранилище в памяти для одного пользователя. Этому пользователю присваивается имя пользователя user, пароль password и роль USER.
 */
