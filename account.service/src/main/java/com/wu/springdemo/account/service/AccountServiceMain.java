package com.wu.springdemo.account.service;

import brave.sampler.Sampler;
import com.wu.springdemo.account.service.model.Account;
import com.wu.springdemo.account.service.repository.AccountRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountServiceMain.class).web(WebApplicationType.SERVLET).run(args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
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
    AccountRepository repository() {
        AccountRepository repository = new AccountRepository();
        repository.add(new Account("1234567890", 50000, 1L));
        repository.add(new Account("1234567891", 50000, 1L));
        repository.add(new Account("1234567892", 0, 1L));
        repository.add(new Account("1234567893", 50000, 2L));
        repository.add(new Account("1234567894", 0, 2L));
        repository.add(new Account("1234567895", 50000, 2L));
        repository.add(new Account("1234567896", 0, 3L));
        repository.add(new Account("1234567897", 50000, 3L));
        repository.add(new Account("1234567898", 50000, 3L));
        return repository;
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
