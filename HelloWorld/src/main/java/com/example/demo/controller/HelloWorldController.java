package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HelloWorldDTO;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	
	
	//uc1
	public String helloWorld() {
		return "Hello From bridgelabz";
	}
	
	//uc2
	@GetMapping("/query")
	@ResponseBody
	public String helloParam(@RequestParam String name) {
	    return "hello " + name+" from Bridgelabz.";
	}
	
	//uc3
	@GetMapping("/param/{first_name}")
	public String helloPathVariable(@PathVariable String first_name) {
		return "Hi, "+ first_name+" from BridgeLabz.";
	}
	
	//uc4
	@PostMapping("/post/requestBody")
	public String helloRequestBody(@RequestBody HelloWorldDTO user) {
		
		return "Hello "+ user.getFirstName()+" "+user.getLastName()+" From BridgeLabz";
		
	}
	
	//uc5
	@PutMapping("/put/{firstName}")
	public String helloPutBody(@PathVariable String firstName,
			@RequestParam String lastName ) {
		return "Hi "+firstName+" "+lastName+" From Brideglabz.";
	}
}
