package com.BankAccount.BankAccount.Domaine.Ports.Secondary;

import com.BankAccount.BankAccount.Domaine.Account;

public interface BankAccountRepository {
    Account findById(long id );
    Account save(Account account);
}
