package com.rsp.akkodis.api.prices.application.rest;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI myOpenAPI() {
		var devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Server URL in Development environment");

		var contact = new Contact();
		contact.setEmail("raulacden@gmail.com");
		contact.setName("raulacden");

		var mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		var info = new Info().title("API - Prices").version("1.0").contact(contact).description("Technical test.")
				.license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}

}
