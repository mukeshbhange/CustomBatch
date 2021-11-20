package com.bl.greeting.services;

import java.util.List;

import com.bl.greeting.model.greeting.Greeting;
import com.bl.greeting.model.user.User;

public interface IGreetingServices {

	String addGreeting(User user);
	Greeting getGreetingById(long id);
	List<Greeting> getAllGreetings();
	void updateGreeting(long id, Greeting greeting);
	void deleteGreeting(long id);
}
