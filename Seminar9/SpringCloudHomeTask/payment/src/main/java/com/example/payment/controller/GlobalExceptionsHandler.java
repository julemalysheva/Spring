package com.example.payment.controller;

import com.example.payment.exeptions.AccountNotFoundException;
import com.example.payment.exeptions.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для контроллеров.
 */
@ControllerAdvice
public class GlobalExceptionsHandler {

    /**
     * Обрабатывает исключение AccountNotFoundException.
     *
     * @param e исключение AccountNotFoundException
     * @return ответ с кодом состояния 404 и сообщением об ошибке
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    /**
     * Обрабатывает исключение InsufficientFundsException.
     *
     * @param e исключение InsufficientFundsException
     * @return ответ с кодом состояния 400 и сообщением об ошибке
     */
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
