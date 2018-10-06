package com.wu.springdemo.eureka.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientDemoMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaClientDemoMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
