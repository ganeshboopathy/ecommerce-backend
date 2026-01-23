package com.sales_analytics.sales_analytics_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SalesAnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesAnalyticsServiceApplication.class, args);
	}

}
