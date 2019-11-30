package com.infy.defect.defect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DefectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefectServiceApplication.class, args);
	}

}
