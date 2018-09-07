package com.myexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NewproductApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewproductApplication.class, args);
	}
}
