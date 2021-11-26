package com.bl.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	public String landmark;
	public String city;
	public String state;
	public String country;
	public String pinCode;
}
