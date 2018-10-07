package com.wu.springdemo.zookeeper.demo.client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "zookeeper-service", path = "/api/demo")
@Component
public interface ZookeeperDemoService {
    @GetMapping(value = "/instance")
    String instance();
}
