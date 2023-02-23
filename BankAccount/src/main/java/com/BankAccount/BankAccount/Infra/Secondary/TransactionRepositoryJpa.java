package com.BankAccount.BankAccount.Infra.Secondary;

import com.BankAccount.BankAccount.Domaine.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositoryJpa extends JpaRepository<Transaction,Long> {
}
