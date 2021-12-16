package com.bl.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LMS_UserDTO {
	
	private String firstName;
	private String lastName;
	private String mobile;
	private String emailId;
	private String profilePath;
	private String password;
}
