package com.example.payment.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования транзакций.
 */
@Aspect
@Component
public class TransactionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionAspect.class);

    /**
     * Логирует начало транзакции.
     *
     * @param joinPoint точка присоединения
     */
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionStart(JoinPoint joinPoint) {
        LOGGER.info("Transaction started for method: {}", joinPoint.getSignature());
    }

    /**
     * Логирует завершение транзакции.
     *
     * @param joinPoint точка присоединения
     */
    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionEnd(JoinPoint joinPoint) {
        LOGGER.info("Transaction ended for method: {}", joinPoint.getSignature());
    }
}
