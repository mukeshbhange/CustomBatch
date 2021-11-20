package com.bl.greeting.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bl.greeting.model.greeting.Greeting;
import com.bl.greeting.model.user.User;
import com.bl.greeting.services.IGreetingServices;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
	
	@Autowired
	private IGreetingServices greetingService;
	
	@GetMapping("")
	public String greeting(@RequestParam(value="firstName",defaultValue="World") String firstName,
			@RequestParam(value="lastName",defaultValue="") String lastName) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return greetingService.addGreeting(user);
	}
	
	@GetMapping("/{id}")
	public Greeting getGreetingById(@PathVariable long id) {
		return greetingService.getGreetingById(id);
	}
	
	@RequestMapping("/all")
	public List<Greeting> allGreetings() {
		return greetingService.getAllGreetings();
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/edit/{id}")
	public void updateGreeting(@RequestBody Greeting greeting,@PathVariable long id) {
		greetingService.updateGreeting(id,greeting);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/delete/{id}")
	public void deleteGreeting(@PathVariable long id) {
		greetingService.deleteGreeting(id);
	}
}
