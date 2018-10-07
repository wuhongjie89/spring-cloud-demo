package com.wu.springdemo.zookeeper.demo.service;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperDemoServiceMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZookeeperDemoServiceMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
