package com.avanade.avanade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class AvanadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvanadeApplication.class, args);
	}

}
