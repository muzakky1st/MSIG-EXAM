package com.siswa.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.base.service", "com.siswa.service"})
@EnableJpaRepositories(basePackages = {"com.base.service.repository", "com.siswa.service.repository"})
@EntityScan(basePackages = "com.base.service.model")
public class SiswaServiceApplication {

	public static void main(String[] args) {
		System.out.println("Starting Siswa Service Application...");
		SpringApplication.run(com.siswa.service.SiswaServiceApplication.class, args);
		System.out.println("Siswa Application Started.");
	}

}
