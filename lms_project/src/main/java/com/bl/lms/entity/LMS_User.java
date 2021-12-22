package com.bl.lms.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bl.lms.dto.LMS_UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "admin")
@NoArgsConstructor
public class LMS_User {
	
	
	@Id
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
		this.id = userDTO.getId();
		this.firstName=userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.emailId=userDTO.getEmailId();
		this.password = userDTO.getPassword();
		this.mobile = userDTO.getMobile();
		this.profilePath = userDTO.getProfilePath();
		this.createStamp = LocalDateTime.now();
	}

}
