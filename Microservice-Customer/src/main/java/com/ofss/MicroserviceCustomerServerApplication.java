package com.ofss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.FluentIterable;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCustomerServerApplication {
	
	public static void main(String[] args) {
		//if u ever want to local any jar location from where the class is coming from, use the below, i used it
		//becz there was a dependency conflict becz of two google guava jars in my .m2 folder
	System.out.println("special value "+FluentIterable.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm());
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
