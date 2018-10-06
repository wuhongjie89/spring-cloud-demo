package com.wu.springdemo.order.service.client;

import com.wu.springdemo.order.service.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/ids")
    List<Product> findByIds(List<Long> ids);

}
