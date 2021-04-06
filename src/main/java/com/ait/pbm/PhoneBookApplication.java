package com.ait.pbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookApplication {
	private int var = 10;

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
	}

}
