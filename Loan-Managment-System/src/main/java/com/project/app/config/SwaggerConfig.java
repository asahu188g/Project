package com.project.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


/**
 * Configuration class for setting up Swagger documentation for the loan Management API.
 * This class defines the OpenAPI specifications and metadata for the API
 * documentation.
 */
@Configuration
public class SwaggerConfig {

	private static final String API_TITLE = "loan Management API";

	private static final String API_DESCRIPTION = "API documentation for loan Management System";

	private static final String API_VERSION = "1.0";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(API_TITLE).description(API_DESCRIPTION).version(API_VERSION));
	}

}