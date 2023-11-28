package com.carfix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CarfixApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarfixApplication.class, args);
	}

}
