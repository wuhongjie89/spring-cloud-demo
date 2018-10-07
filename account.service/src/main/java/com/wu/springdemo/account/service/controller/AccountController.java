package com.wu.springdemo.account.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.springdemo.account.service.model.Account;
import com.wu.springdemo.account.service.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    AccountRepository repository;

    @PostMapping
    public Account add(@RequestBody Account account) {
        return repository.add(account);
    }

    @PutMapping
    public Account update(@RequestBody Account account) {
        return repository.update(account);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount) throws JsonProcessingException {
        Account account = repository.findById(id);
        LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
        account.setBalance(account.getBalance() - amount);
        LOGGER.info("Current balance: {}", mapper.writeValueAsString(Collections.singletonMap("balance", account.getBalance())));
        return repository.update(account);
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") Long id) throws JsonProcessingException {
        Account account = repository.findById(id);
        LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
        return account;
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> findByCustomerId(@PathVariable("customerId") Long customerId) throws JsonProcessingException {
        List<Account> accounts = repository.findByCustomer(customerId);
        LOGGER.info("Account found: {}", mapper.writeValueAsString(accounts));
        return accounts;
    }

    @PostMapping("/ids")
    public List<Account> find(@RequestBody List<Long> ids) {
        return repository.find(ids);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }
}
