package com.BankAccount.BankAccount.Domaine.Ports.Secondary;

import com.BankAccount.BankAccount.Domaine.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
}
