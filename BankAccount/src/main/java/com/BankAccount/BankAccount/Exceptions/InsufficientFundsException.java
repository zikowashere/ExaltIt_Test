package com.BankAccount.BankAccount.Exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
