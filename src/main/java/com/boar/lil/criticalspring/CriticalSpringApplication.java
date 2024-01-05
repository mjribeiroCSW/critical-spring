package com.boar.lil.criticalspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// H2 database creation
@EnableJpaRepositories(basePackages = "com.boar.lil.h2entity")
@EntityScan(basePackages = "com.boar.lil.h2entity")

// enables swagger components from controllers
@ComponentScan(basePackages = "com.boar.lil")

// makes available on the code settings on application.properties
@PropertySource("classpath:application.properties")

public class CriticalSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriticalSpringApplication.class, args);
	}

}
