package com.BankAccount.BankAccount.Infra.Secondary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class H2BankAccount implements AccountRepository {

    private AccountRepositoryJpa accountRepositoryJpa;
    @Autowired
    public H2BankAccount( AccountRepositoryJpa accountRepositoryJpa){
         this.accountRepositoryJpa = accountRepositoryJpa;
    }

    @Override
    public Optional<Account> findByNumber(Long number) {
        return accountRepositoryJpa.findById(number);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepositoryJpa.save(account);
    }
}
