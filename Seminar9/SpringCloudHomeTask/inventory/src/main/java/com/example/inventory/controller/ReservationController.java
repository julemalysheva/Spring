package com.example.inventory.controller;

import com.example.inventory.domain.Reservation;
import com.example.inventory.exeption.NotEnoughQuantityException;
import com.example.inventory.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Reservation>> getReservationsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reservationService.getReservationsByProductId(productId));
    }


    @PostMapping("/products/{productId}")
    public ResponseEntity<?> createReservation(@PathVariable Long productId,
                                               @RequestParam int quantity) {
        try {
            Reservation reservation = reservationService.createReservation(productId, quantity);
            return ResponseEntity.ok(reservation);
        } catch (NotEnoughQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @ExceptionHandler(NotEnoughQuantityException.class)
    public ResponseEntity<String> handleNotEnoughQuantityException(NotEnoughQuantityException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }


}
