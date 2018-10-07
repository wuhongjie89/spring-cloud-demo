package com.wu.springdemo.zookeeper.demo.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperDemoClientMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZookeeperDemoClientMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
