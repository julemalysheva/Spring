package com.example.payment.service;

import com.example.payment.domain.Account;
import com.example.payment.exeptions.AccountNotFoundException;
import com.example.payment.exeptions.InsufficientFundsException;
import com.example.payment.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = getAccountById(fromAccountId);
        Account toAccount = getAccountById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) >= 0) {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
        } else {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }
}
