package com.wu.springdemo.account.service.repository;

import com.wu.springdemo.account.service.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    public Account add(Account account) {
        account.setId((long) (this.accounts.size() + 1));
        this.accounts.add(account);
        return account;
    }

    public Account update(Account account) {
        accounts.set(account.getId().intValue() - 1, account);
        return account;
    }

    public Account findById(Long id) {
        Optional<Account> account = accounts.stream().filter(a -> a.getId().equals(id)).findFirst();
        return account.orElse(null);
    }

    public void delete(Long id) {
        accounts.remove(id.intValue());
    }

    public List<Account> find(List<Long> ids) {
        return accounts.stream().filter(a -> ids.contains(a.getId())).collect(Collectors.toList());
    }

    public List<Account> findByCustomer(Long customerId) {
        return accounts.stream().filter(a -> a.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
