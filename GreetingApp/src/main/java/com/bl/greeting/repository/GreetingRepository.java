package com.bl.greeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bl.greeting.model.greeting.Greeting;


@EnableJpaRepositories
public interface GreetingRepository extends JpaRepository<Greeting,Long>{
	
}
