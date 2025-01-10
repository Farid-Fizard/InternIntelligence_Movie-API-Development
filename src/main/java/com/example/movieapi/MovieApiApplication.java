package com.example.movieapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info= @Info(
				title = "Movie Database API",
				version = "v1.0.0",
				description ="This API allows CRUD operations for managing movie data.",
				contact = @Contact(name = "Farid Hakhiyev", email = "hakhiyevfarid@gmail.com")
		)
)
public class MovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

}
