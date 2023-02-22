package com.BankAccount.BankAccount.Exceptions;

import jakarta.persistence.EntityNotFoundException;

public class AccountNotFound extends EntityNotFoundException {
    private String message;

    public AccountNotFound() {
        super("le compte bancaire que vous cherchez n'exite pas ");
    }
}
