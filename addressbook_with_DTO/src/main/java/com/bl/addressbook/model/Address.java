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
import javax.validation.constraints.Pattern;

import com.bl.addressbook.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Data
@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Pattern( regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "First Letter of Landmark Must be capital")
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
	
	public Address(AddressDTO addressDTO) {
		this.landmark = addressDTO.landmark;
		this.city = addressDTO.city;
		this.state = addressDTO.state;
		this.country = addressDTO.country;
		this.pinCode = addressDTO.pinCode;
	}
}
