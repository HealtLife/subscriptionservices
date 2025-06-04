package com.acme.nutrimove.subscriptionservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.acme.nutrimove.subscriptionservices.backend.external.clients")
@SpringBootApplication
public class SubscriptionservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionservicesApplication.class, args);
    }

}
