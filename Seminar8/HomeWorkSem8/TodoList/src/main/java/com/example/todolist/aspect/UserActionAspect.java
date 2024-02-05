package com.example.todolist.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Аспект UserActionAspect предоставляет механизм отслеживания и логирования
 * действий пользователя в методах, отмеченных аннотацией TrackUserAction.
 */

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class UserActionAspect {

    /**
     * Метод trackUserAction выполняется вокруг методов, отмеченных аннотацией TrackUserAction,
     * для логирования информации о действиях пользователя.
     *
     * @param joinPoint Объект ProceedingJoinPoint для выполнения соединенной точки (вызываемого метода).
     * @return Результат выполнения вызываемого метода.
     * @throws Throwable Исключение, которое может быть брошено при выполнении соединенной точки.
     */
    @Around("@annotation(com.example.todolist.aspect.TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получение информации о вызываемом методе
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // Получение информации о пользователе из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Логирование начала вызываемого метода
        log.info("Действие пользователя: {} - Метод {}.{} вызван с аргументами: {}", username, className,
                methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // Выполнение вызываемого метода
        long executionTime = System.currentTimeMillis() - start;

        // Логирование завершения вызываемого метода
        log.info("Действие пользователя: {} - Метод {} выполнился (Время выполнения: {}ms) с результатом - {} ",
                username, methodName, executionTime, result);

        return result;
    }

}
