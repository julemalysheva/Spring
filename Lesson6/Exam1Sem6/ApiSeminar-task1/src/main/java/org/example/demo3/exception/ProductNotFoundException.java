package org.example.demo3.exception;

public class ProductNotFoundException extends RuntimeException{
    /**
     * Конструктор для создания экземпляра исключения с пользовательским сообщением.
     * @param message Сообщение об ошибке, описывающее причину исключения.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
