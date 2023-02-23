package com.BankAccount.BankAccount.Infra.Secondary;

import com.BankAccount.BankAccount.Domaine.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositoryJpa extends JpaRepository<Account,Long> {
}
