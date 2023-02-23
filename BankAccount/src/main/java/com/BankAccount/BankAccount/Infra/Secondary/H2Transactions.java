package com.BankAccount.BankAccount.Infra.Secondary;

import com.BankAccount.BankAccount.Domaine.Ports.Secondary.TransactionRepository;
import com.BankAccount.BankAccount.Domaine.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class H2Transactions implements TransactionRepository {
    private TransactionRepositoryJpa transactionRepositoryJpa;

    @Autowired
    public H2Transactions(TransactionRepositoryJpa transactionRepositoryJpa){
        this.transactionRepositoryJpa = transactionRepositoryJpa;
    }
    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepositoryJpa.save(transaction);
    }
}
