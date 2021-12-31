package com.bl.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@ComponentScan({"com.bl.addressbook"})
@EnableJpaRepositories("com.bl.addressbook.repository")
public class AddressbookApplication{

	public static void main(String[] args) {
		SpringApplication.run(AddressbookApplication.class, args);
	}
}

