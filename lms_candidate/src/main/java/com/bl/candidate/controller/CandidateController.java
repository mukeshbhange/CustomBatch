package com.bl.candidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bl.candidate.dto.CandidateDTO;
import com.bl.candidate.dto.Response;
import com.bl.candidate.exception.LoginException;
import com.bl.candidate.exception.UserNotFoundException;
import com.bl.candidate.model.Candidate;
import com.bl.candidate.services.ICandidateServices;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	private ICandidateServices candidateServices;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add")
	public ResponseEntity<Response> add(@RequestHeader String token,@RequestBody CandidateDTO candidate) throws LoginException{
		boolean isAdmin = restTemplate.getForObject("http://lms-admin/verify?token="+token,Boolean.class);
		if(isAdmin) {
		Candidate savedUser =candidateServices.addUser(token,candidate);
		Response response = new Response("Added User SuccessFully",(long)200,savedUser);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@GetMapping("/getProfile")
	public ResponseEntity<Response> getProfile(@RequestHeader String token,@RequestHeader long id) throws LoginException, UserNotFoundException{
		boolean isAdmin = restTemplate.getForObject("http://lms-admin/verify?token="+token,Boolean.class);
		
		if(isAdmin) {
			Candidate profile =candidateServices.getProfile(token,id);
			Response response = new Response("Profile got SuccessFully",(long)200,profile);
			return new ResponseEntity<Response>(response,HttpStatus.OK);	
		}else {
			throw new LoginException("Token / id is incoorect");
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestHeader String token,@RequestHeader long id) throws LoginException, UserNotFoundException{
		boolean isAdmin = restTemplate.getForObject("http://lms-admin/verify?token="+token,Boolean.class);
		if(isAdmin) {
			Candidate profile =candidateServices.delete(token,id);
			Response response = new Response("Profile got SuccessFully",(long)200,profile);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@GetMapping("/allcandidates")
	public ResponseEntity<Response> getAllCandidates(@RequestHeader String token) throws LoginException, UserNotFoundException{
		boolean isAdmin = restTemplate.getForObject("http://lms-admin/verify?token="+token,Boolean.class);
		if(isAdmin) {
			List<Candidate> allProfiles =candidateServices.getAllCandidates(token);
			Response response = new Response("All Candidates got SuccessFully",(long)200,allProfiles);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Response> editUser(@RequestHeader String token, @RequestHeader long id,  @RequestBody CandidateDTO user) throws UserNotFoundException, LoginException {
		boolean isAdmin = restTemplate.getForObject("http://lms-admin/verify?token="+token,Boolean.class);
		if(isAdmin) {
			Candidate updatedUser = candidateServices.editUser(token, id, user);
			Response response = new Response("Updated user successfully",(long)200, updatedUser);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}

}
