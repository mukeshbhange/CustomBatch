package com.bl.greeting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bl.greeting.model.greeting.Greeting;
import com.bl.greeting.model.user.User;
import com.bl.greeting.repository.GreetingRepository;


@Service
public class GreetingService implements IGreetingServices{
	
	private static final String template = "hello %s!";
	private static AtomicLong counter = new AtomicLong();
	
	
	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
	public String addGreeting(User user) {
		String message = String.format(template, user.toString().isEmpty()?"Hello World":
			user.getFirstName().isEmpty()?user.getLastName():user.getLastName().isEmpty()?user.getFirstName()
					:user.getFirstName()+" "+user.getLastName());
		greetingRepository.save(new Greeting(counter.incrementAndGet(),message));
		return message;
	}

	@Override
	public Greeting getGreetingById(long id) {
		return greetingRepository.findById(id).get();
	}

	@Override
	public List<Greeting> getAllGreetings() {
		List<Greeting> greetings = new ArrayList<>();
		greetingRepository.findAll().forEach(greetings::add);
		return greetings;
	}

	@Override
	public void updateGreeting(long id, Greeting greeting) {
		greetingRepository.save(greeting);
	}

	@Override
	public void deleteGreeting(long id) {
		greetingRepository.deleteById(id);
	}
}
