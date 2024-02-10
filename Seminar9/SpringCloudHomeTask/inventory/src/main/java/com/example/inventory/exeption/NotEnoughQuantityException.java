package com.example.inventory.exeption;

public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException(String message) {
        super(message);
    }
}
