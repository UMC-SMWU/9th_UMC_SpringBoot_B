package com.example.chapter4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Chapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	}

}
