package com.Lucas.virtual_wallet_server.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException() {
        super("Contact not found");
    }
}
