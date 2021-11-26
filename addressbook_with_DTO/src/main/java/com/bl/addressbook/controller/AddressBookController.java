package com.bl.addressbook.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exceptions.AddressBookExceptions;
import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.response.Response;
import com.bl.addressbook.servces.IAddressBookServices;
import com.bl.addressbook.util.JwtUtil;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
	
	
	@Autowired
	JwtUtil uToken;
	
	
	@Autowired
	IAddressBookServices services;
	
	@GetMapping("/getall")
	public ResponseEntity<Response> getAll() {
		if(services.getAllUsers().isEmpty()) {
			throw new AddressBookExceptions("No Address in Database");
		}else {
			Response response =  new Response((long)200,"Got All Data",services.getAllUsers());	
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("get")
	public ResponseEntity<Response> getUser(@RequestHeader String token) throws UserNotFoundException {
		Response response =  new Response((long)200,"Fetched User Data",services.getOne(token));
		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addUser(@Valid @RequestBody UserDTO user) {
		UserData userdata = services.addUserData(user);
		Response response = new Response((long)200,"User Added SuccessFully",uToken.createToken(userdata.getUser_id()));
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestHeader String token) throws UserNotFoundException{
		 UserData deleteddata = services.deleteUser(token);
		 Response response = new Response((long)200,"User Deleted SuccessFully",deleteddata);
		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}
	
	@PutMapping("/edit")
	public ResponseEntity<Response> update(@Valid @RequestBody UserData userData,@RequestHeader String token) throws UserNotFoundException {
		Response response = new Response((long)200,"User Updated Successfully", services.updateUser(token,userData));
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
}
