package com.BankAccount.BankAccount.Domaine.Ports.Secondary;

import static org.mockito.Mockito.verify;

import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Infra.Secondary.H2Transactions;
import com.BankAccount.BankAccount.Infra.Secondary.TransactionRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class H2TransactionsTest {

    @Mock
    private TransactionRepositoryJpa transactionRepositoryJpa;

    @InjectMocks
    private H2Transactions h2Transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTransaction() {
        // Arrange
        Transaction transaction = new Transaction();

        // Act
        h2Transactions.addTransaction(transaction);

        // Assert
        verify(transactionRepositoryJpa).save(transaction);
    }
}
