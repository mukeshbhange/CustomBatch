package com.bl.addressbook.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	@Pattern( regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "First Letter of Landmark Must be capital")
	public String landmark;
	
	@NotBlank(message="Address field city Cant be Empty")
	public String city;
	
	@NotBlank(message="Address field state Cant be Empty")
	public String state;
	
	@NotBlank(message="Address field country Cant be Empty")
	public String country;
	
	@NotBlank(message="Address field pincode Cant be Empty")
	public String pinCode;
}
