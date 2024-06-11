package com.gundemgaming.fukantin;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info =	@Info(
				title = "FU Kantin Spring Boot Application",
				description = "Backend application of FU Kantin",
				version = "v1.0",
				contact = @Contact(
						name = "Muhammed Duzgun",
						email = "muhammedduzgun00@gmail.com",
						url = "https://www.gundemgaming.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.gundemgaming.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "FU Kantin Spring Boot Application Github Repository",
				url = "https://github.com/MuhammedDuzgun/fukantin"
		)

)
@SpringBootApplication
public class FukantinApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(FukantinApplication.class, args);
	}

}
