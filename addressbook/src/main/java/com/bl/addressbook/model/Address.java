package com.bl.addressbook.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bl.addressbook.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Data
@Entity
@Table(name = "user_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	@JsonBackReference
	private UserData user;
	
	public Address() {
		
	}

	public Address(String landmark, String city, String state, String country, String pinCode) {
		super();
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
	
	
	
/*	public Address(AddressDTO addDTO) {
		this.landmark = addDTO.landmark;
		this.city = addDTO.city;
		this.state = addDTO.state;
		this.country = addDTO.country;
	}*/
}
