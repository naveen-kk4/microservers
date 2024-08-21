package com.microservices.accounts;

import com.microservices.accounts.Audit.AuditAwareImpl;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// this annotation is a part of implementing automating the auditing process
// auditorAwareRef is the bean name that we use to implement the auditing process i.e., AuditAwareImpl class
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
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
				description = "EazyBank Account microservice REST API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html"
		)

)
/*
this is the way to do component scanning for bean creation in case our controller , repository or entity layers are not present within the same root package
@ComponentScans({ @ComponentScan("com.eazybytes.accounts.controller") })
@EnableJpaRepositories("com.eazybytes.accounts.repository")
@EntityScan("com.eazybytes.accounts.model")*/
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
