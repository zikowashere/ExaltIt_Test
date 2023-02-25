package com.BankAccount.BankAccount.Domaine.Ports.Secondary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Infra.Secondary.AccountRepositoryJpa;
import com.BankAccount.BankAccount.Infra.Secondary.H2BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class H2BankAccountTest {

    @Mock
    private AccountRepositoryJpa accountRepositoryJpa;

    @InjectMocks
    private H2BankAccount h2BankAccount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByNumber() {
        // Arrange
        long accountNumber = 5;
        Account account = new Account();
        account.setId(accountNumber);
        when(accountRepositoryJpa.findById(accountNumber)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> result = h2BankAccount.findByNumber(accountNumber);

        // Assert
        assertEquals(account, result.get());
    }

    @Test
    void testSaveAccount() {
        // Arrange
        Account account = new Account();
        when(accountRepositoryJpa.save(account)).thenReturn(account);

        // Act
        Account result = h2BankAccount.saveAccount(account);

        // Assert
        assertEquals(account, result);
    }
}



