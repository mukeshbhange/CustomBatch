package com.bl.e_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LmsEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsEurekaserverApplication.class, args);
	}

}
