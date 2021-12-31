package com.bl.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class LmsZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsZuulGatewayApplication.class, args);
	}

}
