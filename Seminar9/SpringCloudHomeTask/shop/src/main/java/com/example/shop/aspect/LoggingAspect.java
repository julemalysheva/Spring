package com.example.shop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования времени выполнения методов сервисов.
 */

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Измеряет время выполнения метода и логирует его.
     *
     * @param joinPoint точка соединения
     * @return результат выполнения метода
     * @throws Throwable если возникает исключение во время выполнения метода
     */
    @Around("execution(* com.example.shop.service.*.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        LOGGER.info("Method {} execution time: {} ms", joinPoint.getSignature(), executionTime);

        return result;
    }
}

