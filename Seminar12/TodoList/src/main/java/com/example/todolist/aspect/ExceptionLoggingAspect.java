package com.example.todolist.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Аспект ExceptionLoggingAspect предоставляет механизм отслеживания и логирования
 * исключений, брошенных в методах приложения.
 */

@Aspect
@Component
@Slf4j
public class ExceptionLoggingAspect {

    /**
     * Метод logException выполняется после бросания исключения в любом методе
     * приложения, для логирования информации об исключении и пользователях.
     *
     * @param joinPoint Объект JoinPoint для доступа к информации о соединенной точке.
     * @param ex        Брошенное исключение.
     */
    @AfterThrowing(pointcut = "execution(* com.example.todolist..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().toShortString();
        // Получаем информацию о пользователе из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Логирование в консоль и в файл error.log согласно настроек в logback-spring.xml
        log.error("Действие пользователя: {} - метод {} вызвал исключение {}", username,
                methodName, ex.getMessage());

    }
}
