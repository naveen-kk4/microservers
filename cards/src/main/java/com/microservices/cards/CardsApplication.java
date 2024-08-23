package com.microservices.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards Microservice REST API Documentation",
				description = "EazyBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Naveen K",
						email="naveennig2001@gmail.com",
						url="https://www.linkedin.com/in/naveen-kk4/"
				),
				license = @License(
						name = "Apache 2.0",
						url="https://www.linkedin.com/in/naveen-kk4/"

				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Cards microservice REST API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
