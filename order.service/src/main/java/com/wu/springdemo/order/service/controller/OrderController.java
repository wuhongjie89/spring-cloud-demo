package com.wu.springdemo.order.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.springdemo.order.service.client.AccountClient;
import com.wu.springdemo.order.service.client.CustomerClient;
import com.wu.springdemo.order.service.client.ProductClient;
import com.wu.springdemo.order.service.model.Account;
import com.wu.springdemo.order.service.model.Customer;
import com.wu.springdemo.order.service.model.Order;
import com.wu.springdemo.order.service.model.OrderStatus;
import com.wu.springdemo.order.service.model.Product;
import com.wu.springdemo.order.service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private ObjectMapper mapper = new ObjectMapper();

    private final OrderRepository repository;
    private final AccountClient accountClient;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    @Autowired
    public OrderController(OrderRepository repository, AccountClient accountClient, CustomerClient customerClient, ProductClient productClient) {
        this.repository = repository;
        this.accountClient = accountClient;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    @PostMapping
    public Order prepare(@RequestBody Order order) throws JsonProcessingException {
        int price = 0;
        List<Product> products = productClient.findByIds(order.getProductIds());
        LOGGER.info("Products found: {}", mapper.writeValueAsString(products));
        Customer customer = customerClient.findByIdWithAccounts(order.getCustomerId());
        LOGGER.info("Customer found: {}", mapper.writeValueAsString(customer));
        for (Product product : products)
            price += product.getPrice();
        final int priceDiscounted = priceDiscount(price, customer);
        LOGGER.info("Discounted price: {}", mapper.writeValueAsString(Collections.singletonMap("price", priceDiscounted)));
        Optional<Account> account = customer.getAccounts().stream().filter(a -> (a.getBalance() > priceDiscounted)).findFirst();
        if (account.isPresent()) {
            order.setAccountId(account.get().getId());
            order.setStatus(OrderStatus.ACCEPTED);
            order.setPrice(priceDiscounted);
            LOGGER.info("Account found: {}", mapper.writeValueAsString(account.get()));
        } else {
            order.setStatus(OrderStatus.REJECTED);
            LOGGER.info("Account not found: {}", mapper.writeValueAsString(customer.getAccounts()));
        }
//        Map<String, String> m = MDC.getCopyOfContextMap();
        return repository.add(order);
    }

    @PutMapping("/{id}")
    public Order accept(@PathVariable Long id) throws JsonProcessingException {
        final Order order = repository.findById(id);
        LOGGER.info("Order found: {}", mapper.writeValueAsString(order));
        accountClient.withdraw(order.getAccountId(), order.getPrice());
        HashMap<String, Object> log = new HashMap<>();
        log.put("accountId", order.getAccountId());
        log.put("price", order.getPrice());
        LOGGER.info("Account modified: {}", mapper.writeValueAsString(log));
        order.setStatus(OrderStatus.DONE);
        LOGGER.info("Order status changed: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
        repository.update(order);
        return order;
    }

    private int priceDiscount(int price, Customer customer) {
        double discount = 0;
        switch (customer.getType()) {
            case REGULAR:
                discount += 0.05;
                break;
            case VIP:
                discount += 0.1;
                break;

            default:
                break;
        }
        int ordersNum = repository.countByCustomerId(customer.getId());
        discount += (ordersNum * 0.01);
        return (int) (price - (price * discount));
    }

}
