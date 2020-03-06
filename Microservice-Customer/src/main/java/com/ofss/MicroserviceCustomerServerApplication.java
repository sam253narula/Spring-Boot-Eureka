package com.ofss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCustomerServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCustomerServerApplication.class, args);
	}

	public static final String STOCKS_SERVICE_URL = "http://STOCK-MICROSERVICE";

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RemoteStockRepository accountRepository() {
		return new RemoteStockRepository(STOCKS_SERVICE_URL);
	}
}
