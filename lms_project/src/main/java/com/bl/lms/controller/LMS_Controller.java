package com.bl.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bl.lms.dto.ForgotPass;
import com.bl.lms.dto.LMS_UserDTO;
import com.bl.lms.entity.LMS_User;
import com.bl.lms.exception.LoginException;
import com.bl.lms.exception.UserNotFoundException;
import com.bl.lms.response.Response;
import com.bl.lms.services.LMSServices;

@RestController
public class LMS_Controller {
	
	@Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    Job job;
	
	@Autowired
	LMSServices lmsServices;
	
	
	@GetMapping("/login")
	public ResponseEntity<Response> checkUser(@RequestHeader String email, @RequestHeader String password) throws LoginException, UserNotFoundException{
		String isLogin = lmsServices.loginUser(email, password);
		Response response = new Response("Login successfully",(long)200, isLogin);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<Response> getUsers(@RequestHeader String loginToken) throws LoginException, UserNotFoundException {
		List<LMS_User> users = lmsServices.getAllUsers(loginToken);
		Response response = new Response("Get all users successfully",(long)200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addUser(@RequestHeader String loginToken,@RequestBody LMS_UserDTO user) throws LoginException{
		LMS_User savedUser =lmsServices.addUser(loginToken,user);
		Response response = new Response("Added User SuccessFully",(long)200,savedUser);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getUser(@RequestHeader String loginToken,@PathVariable long id) throws UserNotFoundException, LoginException {
		LMS_User user = lmsServices.getUser(loginToken, id);
		Response response = new Response("Get user successfully ",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Response> deleteUser(@RequestHeader String loginToken, @PathVariable long id) throws UserNotFoundException, LoginException {
		lmsServices.deleteUser(loginToken,id);
		Response response = new Response("Deleted user successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Response> editUser(@RequestHeader String loginToken, @PathVariable long id,  @RequestBody LMS_UserDTO user) throws UserNotFoundException, LoginException {
		LMS_User updatedUser = lmsServices.editUser(loginToken, id, user);
		Response response = new Response("Updated user successfully",(long)200, updatedUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/forgot")
	ResponseEntity<Response> forgotPass(@RequestBody ForgotPass forgotPass) throws UserNotFoundException{
		Response response = lmsServices.forgot(forgotPass);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/reset/{token}")
	ResponseEntity<Response> resetpass( @RequestParam String password, @PathVariable String token) throws UserNotFoundException {
		Response response = lmsServices.resetPassword(password, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	


	@GetMapping("/loadcv")
	public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

		Map<String, JobParameter> maps = new HashMap<>();
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);

		System.out.println("JobExecution: " + jobExecution.getStatus());

		System.out.println("Batch is Running...");
		System.out.println("kk");
		while (jobExecution.isRunning()) {
			System.out.println("...");
		}

		return jobExecution.getStatus();
	}


}
