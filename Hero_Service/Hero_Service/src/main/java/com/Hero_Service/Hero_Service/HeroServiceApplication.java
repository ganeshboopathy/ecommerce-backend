package com.Hero_Service.Hero_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HeroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroServiceApplication.class, args);
	}

}
