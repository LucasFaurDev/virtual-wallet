package com.Lucas.virtual_wallet_server.controllers;

import com.Lucas.virtual_wallet_server.exceptions.ContactNotFoundException;
import com.Lucas.virtual_wallet_server.models.Contact;
import com.Lucas.virtual_wallet_server.sevices.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long contactId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                contactService.getContactById(contactId)
        );
    }

    @GetMapping
    public ResponseEntity<Contact> getContact(@RequestParam(required = false) String bankKey,
                                              @RequestParam(required = false) String username) {
        if (bankKey != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    contactService.getContactByBankKey(bankKey)
            );
        }

        if (username != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    contactService.getContactByUsername(username)
            );
        }

        throw new ContactNotFoundException();
    }
}
