package com.Lucas.virtual_wallet_server.repository;

import com.Lucas.virtual_wallet_server.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByBankKey(String bankKey);
    Optional<Contact> findByUsername(String username);
    boolean existsByBankKey(String bankKey);
}
