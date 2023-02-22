package com.BankAccount.BankAccount.Domaine;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType type;

    public Transaction(Long id, BigDecimal amount, LocalDateTime timestamp, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
