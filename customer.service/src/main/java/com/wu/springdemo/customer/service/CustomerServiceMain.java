package com.wu.springdemo.customer.service;

import brave.sampler.Sampler;
import com.wu.springdemo.customer.service.model.Customer;
import com.wu.springdemo.customer.service.model.CustomerType;
import com.wu.springdemo.customer.service.repository.CustomerRepository;
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
public class CustomerServiceMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomerServiceMain.class).web(WebApplicationType.SERVLET).run(args);
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
    CustomerRepository repository() {
        CustomerRepository repository = new CustomerRepository();
        repository.add(new Customer("John Scott", CustomerType.NEW));
        repository.add(new Customer("Adam Smith", CustomerType.REGULAR));
        repository.add(new Customer("Jacob Ryan", CustomerType.VIP));
        return repository;
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
