package com.Lucas.virtual_wallet_server.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException()
    {
        super("Username or Password invalid, please enter username and password corrects");
    }
}
