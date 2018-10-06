package com.wu.springdemo.config.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerMain.class).web(WebApplicationType.SERVLET).run(args);
    }
}
