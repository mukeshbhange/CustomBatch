package com.bl.candidate.services;

import java.util.List;

import com.bl.candidate.dto.CandidateDTO;
import com.bl.candidate.exception.LoginException;
import com.bl.candidate.exception.UserNotFoundException;
import com.bl.candidate.model.Candidate;


public interface ICandidateServices {
	
	Candidate addUser(String token, CandidateDTO user) throws LoginException;

	Candidate getProfile(String token, long id) throws LoginException, UserNotFoundException;

	Candidate delete(String token, long id) throws LoginException, UserNotFoundException;

	List<Candidate> getAllCandidates(String token) throws UserNotFoundException, LoginException;

	Candidate editUser(String token, long id, CandidateDTO user) throws LoginException, UserNotFoundException;
}
