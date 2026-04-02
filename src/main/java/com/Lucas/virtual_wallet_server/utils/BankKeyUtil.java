package com.Lucas.virtual_wallet_server.utils;

import com.Lucas.virtual_wallet_server.repository.AccountRepository;
import com.Lucas.virtual_wallet_server.repository.ContactRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BankKeyUtil {

    private final AccountRepository accountRepository;
    private final ContactRepository contactRepository;

    public BankKeyUtil(AccountRepository accountRepository, ContactRepository contactRepository) {
        this.accountRepository = accountRepository;
        this.contactRepository = contactRepository;
    }

    public String createBankKey() {
        String bankKey;
        do {
            bankKey = UUID.randomUUID().toString().substring(0, 20);
        } while (accountRepository.existsByBankKey(bankKey) ||
                contactRepository.existsByBankKey(bankKey));
        return bankKey;
    }
}
