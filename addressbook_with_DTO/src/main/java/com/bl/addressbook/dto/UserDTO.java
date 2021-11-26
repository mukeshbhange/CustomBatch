package com.bl.addressbook.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bl.addressbook.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	@Pattern( regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "First Letter of Name Must be capital")
	public String name;
	
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Email is not in proper format")
	public String email;
	
	@Pattern(regexp="^(\\+\\d{1,3}[- ]?)?\\d{10}$",message="Mobile is not in proper format")
	public String mobileNo;
	
	@Pattern(regexp="male|female",message="Gender Should be male or female")
	public String gender;
	
	@NotEmpty
	public Address address;
}
