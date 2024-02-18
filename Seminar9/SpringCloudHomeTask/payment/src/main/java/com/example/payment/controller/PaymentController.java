package com.example.payment.controller;

import com.example.payment.config.AccountConfig;
import com.example.payment.domain.Account;
import com.example.payment.domain.Payment;
import com.example.payment.service.AccountService;
import com.example.payment.service.PaymentService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Контроллер для работы с платежами.
 */
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @Autowired
    private AccountConfig accountConfig;

    private final Counter requestTransferFundsCounter = Metrics.counter(
            "request_transfer_funds_count");

    /**
     * Переводит средства с одного счета на другой.
     *
     * @param fromAccountId идентификатор счета отправителя
     * @param toAccountId   идентификатор счета получателя
     *                      (опционально, если не указан, используется значение из конфигурации)
     * @param amount        сумма платежа
     * @param reservationId идентификатор резервации
     * @return созданный платеж
     */
    @PostMapping("/transfer")
    public ResponseEntity<Payment> transferFunds(@RequestParam Long fromAccountId,
                                              @RequestParam(required = false) Long toAccountId,
                                              @RequestParam BigDecimal amount,
                                              @RequestParam Long reservationId) {
        if (toAccountId == null) {
            toAccountId = Long.parseLong(accountConfig.getToAccountId());
        }
        requestTransferFundsCounter.increment();
        return ResponseEntity.ok(paymentService.transferFunds(
                fromAccountId, toAccountId, amount, reservationId));
    }

    /**
     * Получает список всех платежей.
     *
     * @return список всех платежей
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
