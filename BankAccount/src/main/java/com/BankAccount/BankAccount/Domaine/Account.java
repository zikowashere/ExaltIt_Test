package com.BankAccount.BankAccount.Domaine;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private long id;

    private BigDecimal balance;

    private List<Transaction> transactions;

    public Account(long id, BigDecimal balance, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Account(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
