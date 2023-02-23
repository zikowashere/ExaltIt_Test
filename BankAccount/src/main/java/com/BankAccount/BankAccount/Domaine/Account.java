package com.BankAccount.BankAccount.Domaine;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal balance;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;


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
