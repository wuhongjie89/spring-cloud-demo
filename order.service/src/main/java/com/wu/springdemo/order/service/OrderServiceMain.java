package com.wu.springdemo.order.service;

import brave.sampler.Sampler;
import com.wu.springdemo.order.service.client.AccountClient;
import com.wu.springdemo.order.service.repository.OrderRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderServiceMain.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    OrderRepository repository() {
        return new OrderRepository();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setAfterMessagePrefix("REQ:");
        return loggingFilter;
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
