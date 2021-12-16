package com.bl.lms.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.lms.dto.LMS_UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class LMS_User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobile;
	private String profilePath;
	private boolean status;
	private String password;
	
	private LocalDateTime createStamp;
	private LocalDateTime updateStamp;
	
	public LMS_User(LMS_UserDTO userDTO) {
		super();
		this.firstName=userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.emailId=userDTO.getEmailId();
		this.password = userDTO.getPassword();
		this.mobile = userDTO.getMobile();
		this.profilePath = userDTO.getProfilePath();
		this.createStamp = LocalDateTime.now();
	}

}
