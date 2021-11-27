package com.bl.addressbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.ResponseDTO;
import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exceptions.AddressBookExceptions;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.servces.IAddressBookServices;
import com.bl.addressbook.util.JwtUtil;

@RestController
@Validated
@RequestMapping("/addressbook")
public class AddressBookController {
	
	
	@Autowired
	JwtUtil uToken;
	
	@Autowired
	IAddressBookServices services;
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseDTO> getAll() {
		if(services.getAllUsers().isEmpty()) {
			throw new AddressBookExceptions("No Address in Database");
		}else {
			ResponseDTO response =  new ResponseDTO("Got All Data",services.getAllUsers());	
			return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getall/sorted")
	public ResponseEntity<ResponseDTO> allUsersSortedByName() {

		ResponseDTO response =  new ResponseDTO("Got All Data",services.allUsersSortedByName());	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}

	@GetMapping("get")
	public ResponseEntity<ResponseDTO> getUser(@RequestHeader String token) {
		ResponseDTO response =  new ResponseDTO("Fetched User Data",services.getOne(token));
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

	}
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO user) {
		UserData userdata = services.addUserData(user);
		ResponseDTO responseDTO = new ResponseDTO("User Added SuccessFully",userdata,uToken.createToken(userdata.getUser_id()));
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> delete(@RequestHeader String token){
		 UserData deleteddata = services.deleteUser(token);
		 ResponseDTO response = new ResponseDTO("User Deleted SuccessFully",deleteddata);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

	}
	
	@PutMapping("/edit")
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody UserData userData,@RequestHeader String token){
		UserData data = services.updateUser(token,userData);
		ResponseDTO response = new ResponseDTO("User Updated Successfully",data);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/getcity")
	public ResponseEntity<ResponseDTO> getByCity(@RequestParam String city ){
		ResponseDTO response =  new ResponseDTO("Got All Data",services.usersByCity(city));	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestParam String email,@RequestParam String password){
		ResponseDTO response =  new ResponseDTO("LogIn is SuccessFull",services.loginToAddressBook(email,password));	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
}
