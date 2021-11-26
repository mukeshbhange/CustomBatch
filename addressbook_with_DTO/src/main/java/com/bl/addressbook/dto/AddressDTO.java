package com.bl.addressbook.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	@NotEmpty
	public String landmark;
	@NotEmpty
	public String city;
	@NotEmpty
	public String state;
	@NotEmpty
	public String country;
	@NotEmpty
	public String pinCode;
}
