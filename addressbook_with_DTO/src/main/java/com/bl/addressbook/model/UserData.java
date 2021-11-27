package com.bl.addressbook.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import com.bl.addressbook.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Data
@Entity
public class UserData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	
	private String name;
	private String email;
	private String password;
	private String mobileNo;
	private String gender;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
	@JsonManagedReference
	@Valid
	private Address address;
	
	public UserData(UserDTO userDTO) {
		super();
		this.name = userDTO.name;
		this.email = userDTO.email;
		this.password = userDTO.password;
		this.mobileNo = userDTO.mobileNo;
		this.gender = userDTO.gender;
		this.address = userDTO.address;
	}
	
	public UserData() {
		
	}
}
