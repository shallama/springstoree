package com.example.springstore.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Test service for spring store",
        version = "1.0.0",
        description = "Swagger configuration"))
public class SwaggerConfiguration {
}
