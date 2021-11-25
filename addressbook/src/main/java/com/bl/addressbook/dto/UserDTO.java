package com.bl.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	public String name;
	public String email;
	public String mobileNo;
	public String gender;
	public AddressDTO addressdto;

}
