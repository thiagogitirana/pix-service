package com.itau.pixservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.itau.*")
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("com.itau.pixservice.resources.repositories.entities")
@EnableJpaRepositories("com.itau.pixservice.resources.repositories")
public class PixServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PixServiceApplication.class, args);
	}

}
