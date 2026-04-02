package com.Lucas.virtual_wallet_server.exceptions;

public class UserNameUniqueException extends RuntimeException {
    public UserNameUniqueException(String username) {
        super("User with username " + username + " already exists");
    }
}
