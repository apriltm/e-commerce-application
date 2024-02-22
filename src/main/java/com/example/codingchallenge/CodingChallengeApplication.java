package com.example.codingchallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ecommerce API", version = "3.1.0", description = "Ecommerce API Information"))
public class CodingChallengeApplication {


	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeApplication.class, args);
	}
}
