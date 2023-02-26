package com.BankAccount.BankAccount.Infra.Primary;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Primary.BankAccountService;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class BankAccountControllerTest {

    @Mock
    private BankAccountService bankAccountService;

    @InjectMocks
    private BankAccountController bankAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBalanceOfAccount() {
        // Arrange
        Long id = 1L;
        BigDecimal balance = BigDecimal.TEN;
        when(bankAccountService.getBalance(id)).thenReturn(balance);

        // Act
        ResponseEntity<BigDecimal> responseEntity = bankAccountController.getBalanceOfAccount(id);

        // Assert
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
        assert(responseEntity.getBody()).equals(balance);
    }

    @Test
    void testDepositeAmountToAccount() {
        // Arrange
        Long id = 1L;
        BigDecimal amount = BigDecimal.TEN;
        Optional<Account> account = Optional.of(new Account());
        when(bankAccountService.depositeMoney(amount, id)).thenReturn(account);

        // Act
        ResponseEntity<Optional<Account>> responseEntity = bankAccountController.depositeAmountToAccount(id, amount);

        // Assert
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
        assert(responseEntity.getBody()).equals(account);
    }

    @Test
    void testWithDrawMoneyFromAccount() throws InsufficientFundsException {
        // Arrange
        Long id = 1L;
        BigDecimal amount = BigDecimal.TEN;
        Optional<Account> account = Optional.of(new Account());

        when(bankAccountService.withDrawMoney(amount, id)).thenReturn(account);

        // Act
        ResponseEntity<Optional<Account>> responseEntity = bankAccountController.withDrawMoneyFromAccount(id, amount);

        // Assert
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
        assert(responseEntity.getBody()).equals(account);
    }

    @Test
    void testGetTransactionsById() {
        // Arrange
        Long id = 1L;
        List<Transaction> transactions = new ArrayList<>();
        when(bankAccountService.getTransactionsById(id)).thenReturn(transactions);

        // Act
        ResponseEntity<List<Transaction>> responseEntity = bankAccountController.getTransactionsById(id);

        // Assert
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
        assert(responseEntity.getBody()).equals(transactions);
    }
}
