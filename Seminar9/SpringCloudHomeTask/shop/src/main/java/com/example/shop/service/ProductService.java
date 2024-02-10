package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.exceptions.NotEnoughQuantityException;
import com.example.shop.exceptions.NotFoundProductException;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с продуктами.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Получает список всех продуктов.
     *
     * @return список всех продуктов
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Получает продукт по идентификатору.
     *
     * @param id идентификатор продукта
     * @return найденный продукт
     * @throws NotFoundProductException если продукт не найден
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundProductException("Product not found"));
    }

    /**
     * Резервирует продукт определенного количества.
     *
     * @param id       идентификатор продукта
     * @param quantity количество для резервации
     * @throws NotEnoughQuantityException если недостаточно товара для резервации
     */
    public void reserveProduct(Long id, int quantity) {
        Product product = getProductById(id);
        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        } else {
            throw new NotEnoughQuantityException("Not enough quantity");
        }

    }

    /**
     * Создает новый продукт.
     *
     * @param product новый продукт
     * @return созданный продукт
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Обновляет информацию о продукте.
     *
     * @param updatedProduct обновленная информация о продукте
     * @return обновленный продукт
     */
    public Product updateProduct(Product updatedProduct) {
        Long id = updatedProduct.getId();
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        return productRepository.save(existingProduct);
    }

    /**
     * Удаляет продукт по идентификатору.
     *
     * @param id идентификатор продукта для удаления
     */
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}

