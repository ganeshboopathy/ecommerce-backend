package com.sales_analytics.sales_analytics_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI salesAnalyticsOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sales Analytics Service API")
                        .description(
                                "API for Sales Analytics Service, providing endpoints for analyzing sales and order data.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
