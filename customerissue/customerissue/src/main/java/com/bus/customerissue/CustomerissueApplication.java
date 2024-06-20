package com.bus.customerissue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.*")
public class CustomerissueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerissueApplication.class, args);
	}

}
