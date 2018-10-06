package com.wu.springdemo.discovery.service;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServiceMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryServiceMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
