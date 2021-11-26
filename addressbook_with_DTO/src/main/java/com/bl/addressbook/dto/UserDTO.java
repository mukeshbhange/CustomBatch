package com.bl.addressbook.dto;

import com.bl.addressbook.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	public String name;
	public String email;
	public String mobileNo;
	public String gender;
	public Address address;
}
