package com.ms.bankaccountservice.web;


import com.ms.bankaccountservice.entities.BankAccount;
import com.ms.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// Rest API implementation

// Rest Controller
@RestController
public class AccountRestController {
    // Dependancy Injection
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    // Get all Accounts
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    // Get a specific account
    @GetMapping("/bankAccounts/{id}")
    // @PathVariable means to take the request param as variable
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException("Account not found"));
    }


    // Add new Bank Account
    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        return  bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        // Updating info
        if(bankAccount.getBalance() != null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt() != null)account.setCreatedAt(new Date());
        if(bankAccount.getType() != null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency() != null)account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);
    }


    // Get a specific account
    @DeleteMapping ("/bankAccounts/{id}")
    // @PathVariable means to take the request param as variable
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }
}
