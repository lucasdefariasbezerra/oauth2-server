package com.auth.tokenserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TokenserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenserverApplication.class, args);
	}
}
