package com.example.shop.controller;

import com.example.shop.domain.Product;
import com.example.shop.exceptions.NotEnoughQuantityException;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с продуктами.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Получает список всех продуктов.
     *
     * @return список всех продуктов
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Получает продукт по идентификатору.
     *
     * @param id идентификатор продукта
     * @return найденный продукт
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Резервирует продукт определенного количества.
     *
     * @param id       идентификатор продукта
     * @param quantity количество для резервации
     * @return статус ответа
     */
    @PostMapping("/{id}/reserve")
    public ResponseEntity<?> reserveProduct(@PathVariable Long id,
                                            @RequestParam int quantity) {
        try {
            productService.reserveProduct(id, quantity);
            return ResponseEntity.ok().build();
        } catch (NotEnoughQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Создает новый продукт.
     *
     * @param product новый продукт
     * @return созданный продукт
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    /**
     * Обновляет информацию о продукте.
     *
     * @param updatedProduct обновленная информация о продукте
     * @return обновленный продукт
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product updatedProduct) {
        return ResponseEntity.ok(productService.updateProduct(updatedProduct));
    }

    /**
     * Удаляет продукт по идентификатору.
     *
     * @param id идентификатор продукта для удаления
     * @return статус ответа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}

