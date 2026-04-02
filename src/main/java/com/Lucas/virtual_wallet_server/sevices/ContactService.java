package com.Lucas.virtual_wallet_server.sevices;

import com.Lucas.virtual_wallet_server.exceptions.ContactNotFoundException;
import com.Lucas.virtual_wallet_server.models.Contact;
import com.Lucas.virtual_wallet_server.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException()
        );
    }

    public Contact getContactByBankKey(String bankKey) {
        return contactRepository.findByBankKey(bankKey).orElseThrow(
                () -> new ContactNotFoundException()
        );
    }

    public Contact getContactByUsername(String username) {
        return contactRepository.findByUsername(username).orElseThrow(
                () -> new ContactNotFoundException()
        );
    }
}
