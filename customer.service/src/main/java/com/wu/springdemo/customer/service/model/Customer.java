package com.wu.springdemo.customer.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private CustomerType type;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }
}
