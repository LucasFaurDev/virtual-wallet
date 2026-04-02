package com.Lucas.virtual_wallet_server.repository;

import com.Lucas.virtual_wallet_server.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByBankKey(String bankKey);
    boolean existsByBankKey(String bankKey);
}
