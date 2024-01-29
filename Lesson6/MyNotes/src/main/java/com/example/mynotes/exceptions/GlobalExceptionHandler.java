package com.example.mynotes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение NoteNotFoundException.
     *
     * @param ex исключение NoteNotFoundException
     * @return ответ с сообщением об ошибке и статусом "Not Found"
     */
    @ExceptionHandler(NoteNotFoundException.class)
    ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
