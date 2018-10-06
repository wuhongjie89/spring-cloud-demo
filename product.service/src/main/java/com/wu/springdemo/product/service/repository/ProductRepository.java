package com.wu.springdemo.product.service.repository;

import com.wu.springdemo.product.service.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public Product add(Product product) {
        product.setId((long) (products.size() + 1));
        products.add(product);
        return product;
    }

    public Product update(Product product) {
        products.set(product.getId().intValue() - 1, product);
        return product;
    }

    public Product findById(Long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return product.orElse(null);
    }

    public void delete(Long id) {
        products.remove(id.intValue());
    }

    public List<Product> find(List<Long> ids) {
        return products.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }
}
