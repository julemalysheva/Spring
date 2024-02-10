package com.example.payment.service;

import com.example.payment.domain.Account;
import com.example.payment.domain.Payment;
import com.example.payment.exeptions.InsufficientFundsException;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для работы с платежами.
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountService accountService;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Переводит средства с одного счета на другой в рамках транзакции, создает платеж в БД,
     * обновляет сущность резервации, добавляя к ней идентификатор созданного платежа путем
     * отправки запроса на сервис inventory-service по имени через discoveryClient.
     *
     * @param fromAccountId  идентификатор счета отправителя
     * @param toAccountId    идентификатор счета получателя
     * @param amount         сумма платежа
     * @param reservationId  идентификатор резервации
     * @return созданный платеж
     * @throws InsufficientFundsException если на счете отправителя недостаточно средств
     */
    @Transactional
    public Payment transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount, Long reservationId) {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) >= 0) {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            accountService.saveAllAccounts(Arrays.asList(fromAccount, toAccount));

            // Создание и сохранение платежа в БД
            Payment newPayment = new Payment();
            newPayment.setAccountId(fromAccountId);
            newPayment.setAmount(amount);
            newPayment.setReservationId(reservationId);
            Payment savePayment = savePayment(newPayment);

            // Отправка запроса на микросервис "Inventory" для обновления идентификатора платежа в резерве
            ServiceInstance instance = discoveryClient.getInstances("inventory-service").get(0);
            String baseUrl = instance.getUri().toString();
            WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();
            webClient.put()
                    .uri("/reservations/{reservationId}/payment/{paymentId}", reservationId, savePayment.getId())
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            return savePayment;

        } else {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }

    /**
     * Сохраняет платеж.
     *
     * @param payment платеж для сохранения
     * @return сохраненный платеж
     */
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    /**
     * Получает список всех платежей.
     *
     * @return список всех платежей
     */
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
}
