package com.Lucas.virtual_wallet_server.exceptions;

public class OperationNotFoundException extends RuntimeException {
    public OperationNotFoundException(String id)
    {
        super("The operation with id " + id + " was not found");
    }
}
