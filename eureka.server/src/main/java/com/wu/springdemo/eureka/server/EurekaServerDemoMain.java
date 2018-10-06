package com.wu.springdemo.eureka.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDemoMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerDemoMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
