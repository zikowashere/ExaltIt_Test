package com.BankAccount.BankAccount.Domaine.Ports.Primary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.AccountRepository;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Domaine.TransactionType;
import com.BankAccount.BankAccount.Exceptions.AccountNotFound;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BankAccountService {
    private AccountRepository repository;

    public BankAccountService(AccountRepository repository){
        this.repository = repository;
    }

    public Optional<Account> depositeMoney(BigDecimal amount, long id) throws AccountNotFound {
        Optional<Account> optionalAccount = repository.findByNumber(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidParameterException("impossible d'injecter ce montant");
            }
            account.setBalance(account.getBalance().add(amount));
            account.getTransactions().add(new Transaction(id, amount, LocalDateTime.now(), TransactionType.DEPOSIT));
            return Optional.of(repository.saveAccount(account));
        } else {
            return Optional.empty();
        }
    }


    public Optional<Account> withDrawMoney(BigDecimal amount, long id) throws AccountNotFound, InsufficientFundsException {
        Optional<Account> optionalAccount = repository.findByNumber(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0)
                throw new InvalidParameterException("impossible de retirer ce montant");
            if (account.getBalance().compareTo(amount) < 0)
                throw new InsufficientFundsException("ressources insuffisantes");
            account.setBalance(account.getBalance().subtract(amount));
            account.getTransactions().add(new Transaction(id, amount, LocalDateTime.now(), TransactionType.WITHDRAWAL));
            return Optional.of(repository.saveAccount(account));
        } else {
            throw new AccountNotFound("Compte avec ID " + id + " non trouvé");
        }
    }

    public BigDecimal getBalance(long id) throws  AccountNotFound{
        Optional<Account> optionalAccount = repository.findByNumber(id);
        BigDecimal balance= null;
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            balance=account.getBalance();
        }

        return  balance;
    }
}
