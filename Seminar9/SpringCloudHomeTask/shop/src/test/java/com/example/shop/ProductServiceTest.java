package com.example.shop;

import com.example.shop.domain.Product;
import com.example.shop.exceptions.NotEnoughQuantityException;
import com.example.shop.exceptions.NotFoundProductException;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", new BigDecimal("10.00"), 5));
        products.add(new Product(2L, "Product 2", new BigDecimal("20.00"), 10));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(products, result);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Product product = new Product(1L, "Product 1", new BigDecimal("10.00"), 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertEquals(product, result);
    }

    @Test
    public void testGetProductByIdNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act Assert
        assertThrows(
                NotFoundProductException.class,
                () -> productService.getProductById(1L));

    }

    @Test
    public void testReserveProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", new BigDecimal("10.00"), 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        productService.reserveProduct(1L, 5);

        // Assert
        verify(productRepository).save(product);
        assertEquals(5, product.getQuantity());
    }

    @Test
    public void testReserveProductNotEnoughQuantity() {
        // Arrange
        Product product = new Product(1L, "Product 1", new BigDecimal("10.00"), 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act Assert
        assertThrows(
                NotEnoughQuantityException.class,
                () -> productService.reserveProduct(1L, 10));
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        Product product = new Product(null, "Product 1", new BigDecimal("10.00"), 5);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        verify(productRepository).save(product);
        assertEquals(product, result);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product(1L, "Product 1", new BigDecimal("10.00"), 5);
        Product updatedProduct = new Product(1L, "Updated Product 1", new BigDecimal("20.00"), 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product result = productService.updateProduct(updatedProduct);

        // Assert
        verify(productRepository).save(existingProduct);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getQuantity(), result.getQuantity());
    }

    @Test

    public void testDeleteProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", new BigDecimal("10.00"), 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        productService.deleteProduct(1L);

        // Assert
        verify(productRepository).delete(product);
    }
}

