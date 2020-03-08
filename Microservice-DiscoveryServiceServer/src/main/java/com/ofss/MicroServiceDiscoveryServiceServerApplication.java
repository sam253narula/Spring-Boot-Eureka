package com.ofss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.google.common.collect.FluentIterable;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceDiscoveryServiceServerApplication {

	public static void main(String[] args) {
		//if u ever want to local any jar location from where the class is coming from, use the below, i used it
				//becz there was a dependency conflict becz of two google guava jars in my .m2 folder
			System.out.println("special value "+FluentIterable.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm());
		SpringApplication.run(MicroServiceDiscoveryServiceServerApplication.class, args);
	}

}
