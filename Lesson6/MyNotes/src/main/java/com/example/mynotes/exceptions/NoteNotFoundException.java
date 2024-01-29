package com.example.mynotes.exceptions;

/**
 * Исключение, которое генерируется, когда заметка не найдена.
 */
public class NoteNotFoundException extends RuntimeException{

    /**
     * Конструктор исключения с указанным сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public NoteNotFoundException(String message) {
        super(message);
    }
}
