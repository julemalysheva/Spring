package com.example.inventory.service;

import com.example.inventory.domain.Reservation;
import com.example.inventory.exeption.NotEnoughQuantityException;
import com.example.inventory.exeption.ResourceNotFoundException;
import com.example.inventory.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Сервис для работы с резервированием продуктов.
 */
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Получает список всех резерваций.
     *
     * @return список всех резерваций
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Получает список резерваций по идентификатору продукта.
     *
     * @param productId идентификатор продукта
     * @return список резерваций для данного продукта
     */
    public List<Reservation> getReservationsByProductId(Long productId) {
        return reservationRepository.findByProductId(productId);
    }

    /**
     * Создает новую резервацию для продукта, отправляя запрос на сервис shop-service
     * по имени через discoveryClient.
     *
     * @param productId идентификатор продукта
     * @param quantity  количество продукта для резервации
     * @return созданная резервация
     * @throws NotEnoughQuantityException если недостаточно продукта для резервации
     */
    @Transactional
    public Reservation createReservation(Long productId, int quantity) {
        ServiceInstance instance = discoveryClient.getInstances("shop-service").get(0);
        String baseUrl = instance.getUri().toString();
        WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();

        ClientResponse response = webClient.post()
                .uri("/products/{id}/reserve?quantity={quantity}", productId, quantity)
                .exchange()
                .block();

        if (response.statusCode().is2xxSuccessful()) {
            Reservation reservation = new Reservation();
            reservation.setProductId(productId);
            reservation.setQuantity(quantity);

            reservationRepository.save(reservation);
            return reservation;
        } else {
            throw new NotEnoughQuantityException("Not enough quantity");
        }
    }

    /**
     * Обновляет идентификатор платежа для резервации.
     *
     * @param reservationId идентификатор резервации
     * @param paymentId     идентификатор платежа
     * @throws ResourceNotFoundException если резервация не найдена
     */
    public void updateReservationPaymentId(Long reservationId, Long paymentId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        reservation.setPaymentId(paymentId);
        reservationRepository.save(reservation);
    }


}

