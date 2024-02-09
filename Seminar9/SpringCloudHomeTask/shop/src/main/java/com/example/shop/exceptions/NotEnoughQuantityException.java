package com.example.shop.exceptions;

public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException(String message) {
        super(message);
    }
}
