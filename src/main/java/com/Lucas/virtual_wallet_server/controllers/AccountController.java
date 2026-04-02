package com.Lucas.virtual_wallet_server.controllers;

import com.Lucas.virtual_wallet_server.models.Account;
import com.Lucas.virtual_wallet_server.models.Contact;
import com.Lucas.virtual_wallet_server.models.Dto.ContactDto;
import com.Lucas.virtual_wallet_server.models.Dto.OperationDto;
import com.Lucas.virtual_wallet_server.models.Dto.UpdateContactDto;
import com.Lucas.virtual_wallet_server.sevices.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<Account> getAccount(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(
                accountService.getAccount(username)
        );
    }

    @PostMapping("/operations")
    public ResponseEntity<Account> createOperation(@RequestBody @Valid OperationDto operationDto,
                                                   Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                accountService.createOperation(operationDto, username)
        );
    }

    @PostMapping("/contacts")
    public ResponseEntity<Account> createContact(@RequestBody @Valid ContactDto contactDto,
                                                 Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                accountService.createContact(contactDto, username)
        );
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(@RequestBody @Valid UpdateContactDto updateContactDto,
                                                 @PathVariable Long contactId,
                                                 Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(
                accountService.updateContact(updateContactDto, username, contactId)
        );
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Void> removeContactById(@PathVariable Long contactId,
                                                  Authentication authentication) {
        String username = authentication.getName();
        accountService.removeContact(contactId, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
