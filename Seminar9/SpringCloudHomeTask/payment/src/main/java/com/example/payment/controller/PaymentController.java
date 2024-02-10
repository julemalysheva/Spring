package com.example.payment.controller;

import com.example.payment.domain.Account;
import com.example.payment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@RequestParam Long fromAccountId,
                                              @RequestParam Long toAccountId,
                                              @RequestParam BigDecimal amount) {
        accountService.transferFunds(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }
}
