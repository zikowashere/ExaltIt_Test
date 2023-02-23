package com.BankAccount.BankAccount.Infra.Primary;

import com.BankAccount.BankAccount.Domaine.Account;
import com.BankAccount.BankAccount.Domaine.Ports.Primary.BankAccountService;
import com.BankAccount.BankAccount.Domaine.Transaction;
import com.BankAccount.BankAccount.Exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bankAccount")
public class BankAccountController {
    private BankAccountService bankAccountService;
    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(value = "/{id}/balance")
    public ResponseEntity<BigDecimal>getBalanceOfAccount(@PathVariable Long id){
         BigDecimal balance;

         balance = bankAccountService.getBalance(id);
         return new ResponseEntity<>(balance, HttpStatus.OK);

    }
    @PostMapping(value = "/{id}/deposit")
    public ResponseEntity<Optional<Account>> depositeAmountToAccount(@PathVariable Long id, @RequestParam BigDecimal amount) {
        Optional<Account> account=null;
        try {
           account = bankAccountService.depositeMoney(amount, id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @PostMapping(value ="/{id}/withdraw")
    public ResponseEntity<Optional<Account>> withDrawMoneyFromAccount(@PathVariable Long id, @RequestParam BigDecimal amount){
        Optional<Account> account = null;
        try {
             account = bankAccountService.withDrawMoney(amount,id);

        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsById(@PathVariable Long id){
        List<Transaction> transactions = bankAccountService.getTransactionsById(id);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }




}
