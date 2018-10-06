package com.wu.springdemo.customer.service.repository;

import com.wu.springdemo.customer.service.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public Customer add(Customer customer) {
        customer.setId((long) (customers.size() + 1));
        customers.add(customer);
        return customer;
    }

    public Customer update(Customer customer) {
        customers.set(customer.getId().intValue() - 1, customer);
        return customer;
    }

    public Customer findById(Long id) {
        Optional<Customer> customer = customers.stream().filter(p -> p.getId().equals(id)).findFirst();
        return customer.orElse(null);
    }

    public void delete(Long id) {
        customers.remove(id.intValue());
    }

    public List<Customer> find(List<Long> ids) {
        return customers.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }
}
