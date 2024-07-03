package com.guru.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.base.service", "com.guru.service"})
@EnableJpaRepositories(basePackages = {"com.base.service.repository", "com.guru.service.repository"})
@EntityScan(basePackages = "com.base.service.model")
@SpringBootApplication
public class GuruServiceApplication {

    public static void main(String[] args) {
        System.out.println("Starting Guru Service Application...");
        SpringApplication.run(GuruServiceApplication.class, args);
        System.out.println("Guru Service Application Started.");
    }

}
