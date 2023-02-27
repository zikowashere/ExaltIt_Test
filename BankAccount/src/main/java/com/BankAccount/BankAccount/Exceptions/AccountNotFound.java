package com.BankAccount.BankAccount.Exceptions;

import jakarta.persistence.EntityNotFoundException;

public class AccountNotFound extends EntityNotFoundException {


    public AccountNotFound(String  message) {
        super(message);
    }
}
