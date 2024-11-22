package com.shahul.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootRestEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestEmployeeApplication.class, args);
	}

}
