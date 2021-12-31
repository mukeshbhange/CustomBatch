package com.bl.addressbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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



@CrossOrigin(allowedHeaders="*", origins="*")
@RestController
@Validated
@RequestMapping("/addressbook")
public class AddressBookController {
	
	
	@Autowired
	JwtUtil uToken;
	
	@Autowired
	IAddressBookServices services;
	
	
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestHeader String email,@RequestHeader String password){
		ResponseDTO response =  new ResponseDTO("LogIn is SuccessFull",services.loginToAddressBook(email,password));	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseDTO> getAll(@RequestHeader String loginToken) throws Exception {
		if(services.getAllUsers(loginToken).isEmpty()) {
			throw new AddressBookExceptions("No Address in Database");
		}else {
			ResponseDTO response =  new ResponseDTO("Got All Data",services.getAllUsers(loginToken));	
			return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getall/sorted")
	public ResponseEntity<ResponseDTO> allUsersSortedByName() {

		ResponseDTO response =  new ResponseDTO("Got All Data",services.allUsersSortedByName());	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<ResponseDTO> getUser(@RequestHeader String loginToken,@PathVariable long id) {
		ResponseDTO response =  new ResponseDTO("Fetched User Data",services.getOne(loginToken,id));
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

	}
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addUser(@RequestHeader String loginToken,@Valid @RequestBody UserDTO user) {
		UserData userdata = services.addUserData(loginToken,user);
		ResponseDTO responseDTO = new ResponseDTO("User Added SuccessFully",userdata);
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@RequestHeader String loginToken,@PathVariable long id){
		 UserData deleteddata = services.deleteUser(loginToken,id);
		 ResponseDTO response = new ResponseDTO("User Deleted SuccessFully",deleteddata);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<ResponseDTO> update(@RequestHeader String loginToken,@Valid @RequestBody UserData userData,@PathVariable long id){
		UserData data = services.updateUser(loginToken,id,userData);
		ResponseDTO response = new ResponseDTO("User Updated Successfully",data);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/getcity")
	public ResponseEntity<ResponseDTO> getByCity(@RequestHeader String loginToken,@RequestParam String city ){
		ResponseDTO response =  new ResponseDTO("Got All Data",services.usersByCity(loginToken,city));	
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	
}
