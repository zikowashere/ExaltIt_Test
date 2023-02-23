package com.BankAccount.BankAccount.Domaine.Ports.Secondary;

import com.BankAccount.BankAccount.Domaine.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository  {
   Optional<Account> findByNumber(Long number) ;


   Account saveAccount(Account account) ;
}
