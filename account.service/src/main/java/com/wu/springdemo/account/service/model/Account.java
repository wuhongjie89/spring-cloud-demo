package com.wu.springdemo.account.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private Long id;
    private String number;
    private int balance;
    private Long customerId;

    public Account(String number, int balance, Long customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }
}
