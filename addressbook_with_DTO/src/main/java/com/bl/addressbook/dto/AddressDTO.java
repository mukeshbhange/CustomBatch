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
	
	@NotBlank(message="City field city Cant be Empty")
	public String landmark;
	
	@NotBlank(message="City field city Cant be Empty")
	public String city;
	
	@NotBlank(message="Address field state Cant be Empty")
	public String state;
	
	@NotBlank(message="Address field country Cant be Empty")
	public String country;
	
	@NotBlank(message="Address field pincode Cant be Empty")
	public String pinCode;
}
