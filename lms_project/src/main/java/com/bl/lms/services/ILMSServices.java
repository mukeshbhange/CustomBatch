package com.bl.lms.services;

import com.bl.lms.dto.LMS_UserDTO;
import com.bl.lms.entity.LMS_User;
import com.bl.lms.exception.LoginException;
import com.bl.lms.exception.UserNotFoundException;

public interface ILMSServices {
	
	LMS_User addUser(String loginToken,LMS_UserDTO user) throws LoginException;
	String loginUser(String email, String password) throws LoginException, UserNotFoundException;

}
