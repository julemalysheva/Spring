package com.example.inventory.service;

import com.example.inventory.domain.Reservation;
import com.example.inventory.exeption.NotEnoughQuantityException;
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

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private DiscoveryClient discoveryClient;


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByProductId(Long productId) {
        return reservationRepository.findByProductId(productId);
    }

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


}

