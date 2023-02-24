package com.BankAccount.BankAccount.Domaine.Ports.Primary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.AccountRepository;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.TransactionRepository;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Domaine.TransactionType;
import com.BankAccount.BankAccount.Exceptions.AccountNotFound;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public BankAccountService(AccountRepository repository, TransactionRepository transactionRepository){
        this.accountRepository = repository;
        this.transactionRepository = transactionRepository;
    }

    public Optional<Account> depositeMoney(BigDecimal amount, long id) throws AccountNotFound {
        Account account = null;
        Transaction transaction = null;
        Optional<Account> optionalAccount = accountRepository.findByNumber(id);
        if (optionalAccount.isPresent()) {
             account = optionalAccount.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidParameterException("impossible d'injecter ce montant");
            }
            account.setBalance(account.getBalance().add(amount));
            transaction = new Transaction(amount, LocalDateTime.now(), TransactionType.DEPOSIT,account);
            account.getTransactions().add(transaction);
            account.setTransactions(account.getTransactions());
            transactionRepository.addTransaction(transaction);

            return Optional.of(accountRepository.saveAccount(account));
        } else {
            throw new AccountNotFound("Compte avec ID " + id + " non trouvé");
        }
    }


    public Optional<Account> withDrawMoney(BigDecimal amount, long id) throws InsufficientFundsException {
        Account account = null;
        Transaction transaction = null;
        Optional<Account> optionalAccount = accountRepository.findByNumber(id);

        if (optionalAccount.isPresent()) {
             account = optionalAccount.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0)
                throw new InvalidParameterException("impossible de retirer ce montant");
            if (account.getBalance().compareTo(amount) < 0)
                throw new InsufficientFundsException("ressources insuffisantes");

            account.setBalance(account.getBalance().subtract(amount));
            transaction = new Transaction( amount, LocalDateTime.now(), TransactionType.WITHDRAWAL,account);
            account.getTransactions().add(transaction);
            account.setTransactions(account.getTransactions());
            transactionRepository.addTransaction(transaction);

            return Optional.of(accountRepository.saveAccount(account));
        } else {
            throw new AccountNotFound("Compte avec ID " + id + " non trouvé");
        }
    }

    public BigDecimal getBalance(long id) {
        BigDecimal balance= null;
        Account account= null;
        Optional<Account> optionalAccount = accountRepository.findByNumber(id);
        if(optionalAccount.isPresent()){
            account = optionalAccount.get();
            balance=account.getBalance();
        } else
            throw  new AccountNotFound("le compte bancaire que vous cherchez n'existe pas");

        return  balance;
    }

    public List<Transaction> getTransactionsById(Long id)  {
        List<Transaction> transactions = null;
        Account account= null;
        Optional<Account> accountOptional = accountRepository.findByNumber(id);
        if(accountOptional.isPresent()){
            account = accountOptional.get();
            transactions = account.getTransactions();
        }else
            throw  new AccountNotFound("le compte bancaire que vous cherchez n'existe pas");
        return transactions;

    }
}
