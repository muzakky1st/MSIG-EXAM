package com.mapel.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.base.service", "com.mapel.service"})
@EnableJpaRepositories(basePackages = {"com.base.service.repository", "com.mapel.service.repository"})
@EntityScan(basePackages = "com.base.service.model")
public class MapelServiceApplication {

    public static void main(String[] args) {
        System.out.println("Starting Mapel Service Application...");
        SpringApplication.run(MapelServiceApplication.class, args);
        System.out.println("Mapel Application Started.");
    }

}
