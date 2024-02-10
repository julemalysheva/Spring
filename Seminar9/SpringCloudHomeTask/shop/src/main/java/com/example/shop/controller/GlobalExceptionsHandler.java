package com.example.shop.controller;

import com.example.shop.exceptions.NotEnoughQuantityException;
import com.example.shop.exceptions.NotFoundProductException;
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
     * Обрабатывает исключение NotFoundProductException.
     *
     * @param e исключение NotFoundProductException
     * @return ответ с сообщением об ошибке и статусом NOT_FOUND
     */
    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity<String> handleNotFoundProductException(NotFoundProductException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    /**
     * Обрабатывает исключение NotEnoughQuantityException.
     *
     * @param e исключение NotEnoughQuantityException
     * @return ответ с сообщением об ошибке и статусом BAD_REQUEST
     */
    @ExceptionHandler(NotEnoughQuantityException.class)
    public ResponseEntity<String> handleNotEnoughQuantityException(NotEnoughQuantityException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
