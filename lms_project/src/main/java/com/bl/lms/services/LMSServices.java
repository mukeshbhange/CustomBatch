package com.bl.lms.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.lms.dto.ForgotPass;
import com.bl.lms.dto.LMS_UserDTO;
import com.bl.lms.entity.LMS_User;
import com.bl.lms.exception.LoginException;
import com.bl.lms.exception.UserNotFoundException;
import com.bl.lms.repository.LMSRepository;
import com.bl.lms.response.Response;
import com.bl.lms.util.Email;
import com.bl.lms.util.MessageProducer;
import com.bl.lms.util.TokenUtil;


@Service
public class LMSServices implements ILMSServices {
	
	@Autowired
	TokenUtil tokenutil;
	
	@Autowired
	private MessageProducer messageproducer;
	
	
	@Autowired
	private MailServices mailServices;
	
	
	@Autowired
	private Email sendigemail;
	
	@Autowired
	LMSRepository userRepo;
	
	private long user_id = 101;
	private String email = "bhangemukesh98@gmail.com";
	private String password = "Mukesh@000";

	@Override
	public LMS_User addUser(String loginToken, LMS_UserDTO userDTO) throws LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			LMS_User userData = new LMS_User(userDTO);
			userRepo.save(userData);
			return userData;
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public String loginUser(String email, String password) throws LoginException, UserNotFoundException {
		if (email.equals(this.email)) {
			if (password.equals(this.password)) {
				return tokenutil.createToken(this.user_id);
			} else {
				throw new LoginException("Wrong Password!");
			}
		} else {
			throw new UserNotFoundException("User not exist");
		}
	}

	public LMS_User getUser(String loginToken, long id) throws UserNotFoundException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			if(userRepo.findById(id).isEmpty()) {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}else {
				return userRepo.findById(id).get();

			}	
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	public List<LMS_User> getAllUsers(String loginToken) throws UserNotFoundException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			List<LMS_User> userData = new ArrayList<>();
			userRepo.findAll().forEach(userData::add);
			if(userData.isEmpty()) {
				throw new UserNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return userData;
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	public LMS_User deleteUser(String loginToken, long id) throws UserNotFoundException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			if(!userRepo.findById(id).isEmpty()) {
				LMS_User deleted = getUser(loginToken,id);
				userRepo.deleteById(id);
				return deleted;
			}else {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
		
	}

	public LMS_User editUser(String loginToken, long id, LMS_UserDTO user) throws UserNotFoundException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			LMS_User found_user = this.getUser( loginToken,id);

			if(found_user != null) {
				
				found_user.setFirstName(user.getFirstName());
				found_user.setLastName(user.getLastName());
				found_user.setEmailId(user.getEmailId());
				found_user.setMobile(user.getMobile());
				found_user.setPassword(user.getPassword());
				found_user.setProfilePath(user.getProfilePath());
				found_user.setUpdateStamp(LocalDateTime.now());
				userRepo.save(found_user);

				return found_user;
			}else {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@SuppressWarnings("static-access")
	public Response forgot(ForgotPass forgotPass) throws UserNotFoundException {
		String emailId = forgotPass.getEmail();
		Optional<LMS_User> isPresent = userRepo.findByEmailId(emailId);
		
		if(isPresent.isPresent()) {
			sendigemail.setMailTo(forgotPass.getEmail());
			sendigemail.setMailFrom("bhangemukesh98@gmail.com");
			sendigemail.setMailSubject("forgot password Link");
			String token = tokenutil.createToken((isPresent.get().getId()));
			sendigemail.setMailBody(mailServices.getLink("http://localhost:8883/reset/", isPresent.get().getId()));
			mailServices.send(sendigemail.getMailTo(),sendigemail.getMailSubject(),sendigemail.getMailBody());
			return new Response("link is sent.....",(long)200, token);
		}else {
			throw new UserNotFoundException("This User is not Present at this Database");
		}
	}

	@SuppressWarnings("static-access")
	public Response resetPassword(String password2, String token) throws UserNotFoundException {
		long id = tokenutil.decodeToken(token);
		System.out.println(id);
		Optional<LMS_User> user = userRepo.findById(id);
		System.out.println(user.get());
		if(user.isPresent()) {
			user.get().setPassword(password2);
			user.get().setUpdateStamp(LocalDateTime.now());
			sendigemail.setMailTo(user.get().getEmailId());
			sendigemail.setMailFrom("bhangemukesh98@gmail.com");
			sendigemail.setMailSubject("Password Changed Successfully");
			sendigemail.setMailBody("Password Reset Successfully...!");
			mailServices.send(sendigemail.getMailTo(),sendigemail.getMailSubject(),sendigemail.getMailBody());
			userRepo.save(user.get());
			return new Response("New Password Saved SuccessFully....!",(long)200);
			
		}else {
			throw new UserNotFoundException("This User is not Present at this Database");
		}	
	}
}
