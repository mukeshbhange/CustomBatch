package com.bl.addressbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.servces.IAddressBookServices;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
	
	
	@Autowired
	IAddressBookServices services;
	
	@GetMapping("/getall")
	public List<UserData> getAll() {
		return services.getAllUsers();
	}
	
	@GetMapping("get/{id}")
	public Optional<UserData> getOne(@PathVariable long id) {
		return services.getOne(id);
	}
	
	@PostMapping("/add")
	public UserData addUser(@RequestBody UserData user) {
		return services.addUserData(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id){
		 services.deleteUser(id);
		 return "Success";
	}
	
	@PutMapping("/edit/{id}")
	public UserData update(@RequestBody UserData userData,@PathVariable long id) {
		
		return services.updateUser(id,userData);
		
	}
}
