package com.example.inventory.controller.handler;

import com.example.inventory.exeption.NotEnoughQuantityException;
import com.example.inventory.exeption.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для контроллеров.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

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

    /**
     * Обрабатывает исключение ResourceNotFoundException.
     *
     * @param e исключение ResourceNotFoundException
     * @return ответ с сообщением об ошибке и статусом NOT_FOUND
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
