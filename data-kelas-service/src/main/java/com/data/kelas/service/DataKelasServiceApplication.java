package com.data.kelas.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.base.service", "com.data.kelas.service"})
@EnableJpaRepositories(basePackages = "com.base.service.repository")
@EntityScan(basePackages = "com.base.service.model")
public class DataKelasServiceApplication {

	public static void main(String[] args) {
		System.out.println("Starting Data-Kelas Service Application...");
		SpringApplication.run(DataKelasServiceApplication.class, args);
		System.out.println("Data-Kelas Application Started.");

	}

}
