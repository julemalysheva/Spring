package com.example.inventory.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования начала и окончания транзакций.
 */
@Aspect
@Component
public class TransactionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionAspect.class);

    /**
     * Логирует начало транзакции перед выполнением метода помеченного аннотацией @Transactional.
     *
     * @param joinPoint точка соединения
     */
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionStart(JoinPoint joinPoint) {
        LOGGER.info("Transaction started for method: {}", joinPoint.getSignature());
    }

    /**
     * Логирует окончание транзакции после выполнения метода помеченного аннотацией @Transactional.
     *
     * @param joinPoint точка соединения
     */
    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionEnd(JoinPoint joinPoint) {
        LOGGER.info("Transaction ended for method: {}", joinPoint.getSignature());
    }
}

