package com.example.demosecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig_OldVersion extends WebSecurityConfigurerAdapter {
//// Детали настройки будут добавлены позже
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable() // Отключаем защиту CSRF, так как будем использовать JWT
//            .authorizeRequests()
//            .antMatchers("/login").permitAll() // Позволяем всем пользователям доступ к эндпоинту "/login"
//            .anyRequest().authenticated() // Все остальные эндпоинты требуют аутентификации
//            .and()
//            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
////Добавляем наш фильтр аутентификации
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//// Не создаем сессию, так как будем использовать JWT
//}
//}

//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
