package com.wu.springdemo.order.service.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private int price;
}
