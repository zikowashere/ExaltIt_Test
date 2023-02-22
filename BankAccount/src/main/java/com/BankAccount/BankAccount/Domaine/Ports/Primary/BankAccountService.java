package com.BankAccount.BankAccount.Domaine.Ports.Primary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.BankAccountRepository;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Domaine.TransactionType;
import com.BankAccount.BankAccount.Exceptions.AccountNotFound;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Service
public class BankAccountService {
    private BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository){
        this.repository = repository;
    }

    public Account depositeMoney(BigDecimal amount, long id) throws AccountNotFound {

            Account account = repository.findById(id);
            if(amount.compareTo(BigDecimal.ZERO)<=0)
                throw new InvalidParameterException("impossible d'injecter ce montant");
            account.setBalance(account.getBalance().add(amount));
            account.getTransactions().add(new Transaction(id,amount, LocalDateTime.now(), TransactionType.DEPOSIT));
            return repository.save(account);
    }

    public Account withDrawMoney(BigDecimal amount, long id) throws  AccountNotFound,InsufficientFundsException{

        Account account = repository.findById(id);
        if(amount.compareTo(BigDecimal.ZERO)<=0)
            throw  new InvalidParameterException("impossible de retirer ce montant");
        if(account.getBalance().compareTo(BigDecimal.ZERO)<=0)
            throw new InsufficientFundsException("ressources insuffisantes");
        account.setBalance(account.getBalance().subtract(amount));
        account.getTransactions().add(new Transaction(id,amount, LocalDateTime.now(), TransactionType.WITHDRAWAL));
        return  repository.save(account);
    }
    public BigDecimal getBalance(long id) throws  AccountNotFound{
        Account account = repository.findById(id);
        return  account.getBalance();
    }
}
