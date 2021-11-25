package com.bl.addressbook.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bl.addressbook.dto.AddressDTO;
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
	private String mobileNo;
	private String gender;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
	@JsonManagedReference
	private Address address;
	
	public UserData(String name, String email, String mobileNo, String gender, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.address = address;
	}
	
	public UserData() {
		
	}
}
