package com.example.payment.service;

import com.example.payment.domain.Account;
import com.example.payment.exeptions.AccountNotFoundException;
import com.example.payment.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для работы с учетными записями счетов.
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * Получает учетную запись по идентификатору.
     *
     * @param id идентификатор учетной записи
     * @return найденная учетная запись
     * @throws AccountNotFoundException если учетная запись не найдена
     */
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    /**
     * Получает список всех учетных записей.
     *
     * @return список всех учетных записей
     */
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    /**
     * Сохраняет список учетных записей.
     *
     * @param list список учетных записей для сохранения
     */
    public void saveAllAccounts(List<Account> list) {
        accountRepository.saveAll(list);
    }

    /**
     * Сохраняет учетную запись.
     *
     * @param account учетная запись для сохранения
     */
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }


}
