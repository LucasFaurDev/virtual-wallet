package com.Lucas.virtual_wallet_server.sevices;

import com.Lucas.virtual_wallet_server.exceptions.ContactNotFoundException;
import com.Lucas.virtual_wallet_server.exceptions.InsufficientBalanceException;
import com.Lucas.virtual_wallet_server.models.Account;
import com.Lucas.virtual_wallet_server.models.Contact;
import com.Lucas.virtual_wallet_server.models.Dto.ContactDto;
import com.Lucas.virtual_wallet_server.models.Dto.OperationDto;
import com.Lucas.virtual_wallet_server.models.Dto.UpdateContactDto;
import com.Lucas.virtual_wallet_server.models.Operation;
import com.Lucas.virtual_wallet_server.models.User;
import com.Lucas.virtual_wallet_server.repository.AccountRepository;
import com.Lucas.virtual_wallet_server.utils.BankKeyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankKeyUtil bankKeyUtil;
    private final UserService userService;
    private final ContactService contactService;

    private Account getUserAccount(String username) {
        User user = userService.getUserByUsername(username);
        return user.getAccount();
    }

    public AccountService(AccountRepository accountRepository, BankKeyUtil bankKeyUtil,
                          UserService userService, ContactService contactService) {
        this.accountRepository = accountRepository;
        this.bankKeyUtil = bankKeyUtil;
        this.userService = userService;
        this.contactService = contactService;
    }

    public Account getAccount(String username) {
        User user = userService.getUserByUsername(username);

        return user.getAccount();
    }

    @Transactional
    public Account createOperation(OperationDto operationDto, String username) {
        Account account = getUserAccount(username);

        LocalDateTime date = LocalDateTime.now();

        Operation newOperation = new Operation(date, operationDto.getType(),
                operationDto.getAmount(), operationDto.getAddressee());

        newOperation.setAccount(account);

        switch (newOperation.getType()) {
            case DEPOSIT, TRANSFER_RECEIVED ->
                account.setBalance(account.getBalance().add(newOperation.getAmount()));
            case BUY, PAYMENT_SERVICES, WITHDRAW, TRANSFER_SENT ->
            {
                if (account.getBalance().compareTo(newOperation.getAmount()) >= 0) {
                    account.setBalance(account.getBalance().subtract(newOperation.getAmount()));
                } else {
                    throw new InsufficientBalanceException();
                }
            }
        }

        account.getOperations().add(newOperation);

        return accountRepository.save(account);
    }

    @Transactional
    public Account createContact(ContactDto contactDto, String username) {
        Account account = getUserAccount(username);

        String bankKey = bankKeyUtil.createBankKey();

        Contact newContact = new Contact(contactDto.getName(), contactDto.getLastName(),
                contactDto.getPhone(), contactDto.getEmail(), contactDto.getPicture(),
                bankKey, contactDto.getUsername());

        newContact.setAccount(account);

        account.getContacts().add(newContact);

        return accountRepository.save(account);
    }

    @Transactional
    public Contact updateContact(UpdateContactDto updateContactDto,String username,
                                 Long contactId) {
        Account account = getUserAccount(username);

        Contact contact = contactService.getContactById(contactId);

        if (!account.getContacts().contains(contact)) {
            throw new ContactNotFoundException();
        }

        contact.setName(updateContactDto.getName());
        contact.setLastName(updateContactDto.getLastName());
        contact.setEmail(updateContactDto.getEmail());
        contact.setPhone(updateContactDto.getPhone());
        contact.setPicture(updateContactDto.getPicture());
        contact.setUsername(updateContactDto.getUsername());

        return contact;
    }

    @Transactional
    public void removeContact(Long contactId, String username) {
        Account account = getUserAccount(username);
        Contact contact = contactService.getContactById(contactId);

        if (!account.getContacts().contains(contact)) {
            throw new ContactNotFoundException();
        }

        account.getContacts().remove(contact);
    }
}
