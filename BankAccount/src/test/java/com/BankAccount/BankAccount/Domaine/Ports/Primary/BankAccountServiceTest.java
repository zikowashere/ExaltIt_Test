package com.BankAccount.BankAccount.Domaine.Ports.Primary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.AccountRepository;
import com.BankAccount.BankAccount.Domaine.Ports.Secondary.TransactionRepository;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Domaine.TransactionType;
import com.BankAccount.BankAccount.Exceptions.AccountNotFound;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

    

    @Test
    void testGetBalance() throws AccountNotFound {
        // Arrange
        long id = 1L;
        BigDecimal expectedBalance = BigDecimal.TEN;
        Account account = new Account();
        account.setBalance(expectedBalance);
        Optional<Account> optionalAccount = Optional.of(account);

        when(accountRepository.findByNumber(id)).thenReturn(optionalAccount);

        // Act
        BigDecimal result = bankAccountService.getBalance(id);

        // Assert
        assertEquals(expectedBalance, result);
    }

    @Test
    void testGetTransactionsById() throws AccountNotFound {
        // Arrange
        long id = 1L;
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(BigDecimal.TEN, LocalDateTime.now(), TransactionType.DEPOSIT, null));
        Account account = new Account();
        account.setTransactions(expectedTransactions);
        Optional<Account> optionalAccount = Optional.of(account);

        when(accountRepository.findByNumber(id)).thenReturn(optionalAccount);

        // Act
        List<Transaction> result = bankAccountService.getTransactionsById(id);

        // Assert
        assertEquals(expectedTransactions, result);
    }
}
