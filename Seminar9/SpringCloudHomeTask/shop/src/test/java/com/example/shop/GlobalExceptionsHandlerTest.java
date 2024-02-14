package com.example.shop;


import com.example.shop.controller.GlobalExceptionsHandler;
import com.example.shop.exceptions.NotEnoughQuantityException;
import com.example.shop.exceptions.NotFoundProductException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionsHandlerTest {

    @Mock
    private NotFoundProductException notFoundProductException;

    @Mock
    private NotEnoughQuantityException notEnoughQuantityException;

    @InjectMocks
    private GlobalExceptionsHandler globalExceptionsHandler;

    @Test
    void handleNotFoundProductException_ReturnsNotFoundStatus() {
        // Arrange
        when(notFoundProductException.getMessage()).thenReturn("Product not found");

        // Act
        ResponseEntity<String> response = globalExceptionsHandler.handleNotFoundProductException(
                notFoundProductException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }

    @Test
    void handleNotEnoughQuantityException_ReturnsBadRequestStatus() {
        // Arrange
        when(notEnoughQuantityException.getMessage()).thenReturn("Not enough quantity");

        // Act
        ResponseEntity<String> response = globalExceptionsHandler.handleNotEnoughQuantityException(
                notEnoughQuantityException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Not enough quantity", response.getBody());
    }
}


