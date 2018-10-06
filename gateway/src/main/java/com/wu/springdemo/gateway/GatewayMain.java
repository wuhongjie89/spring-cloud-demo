package com.wu.springdemo.gateway;


import brave.sampler.Sampler;
import com.wu.springdemo.gateway.fallback.AccountFallbackProvider;
import com.wu.springdemo.gateway.filter.AddResponseIDHeaderFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayMain.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    AddResponseIDHeaderFilter filter() {
        return new AddResponseIDHeaderFilter();
    }

    @Bean
    AccountFallbackProvider fallback() {
        return new AccountFallbackProvider();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
