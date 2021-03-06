package com.bl.candidate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.candidate.dto.CandidateDTO;
import com.bl.candidate.exception.LoginException;
import com.bl.candidate.exception.UserNotFoundException;
import com.bl.candidate.model.Candidate;
import com.bl.candidate.repository.CandidateRepository;
import com.bl.candidate.util.TokenUtil;



@Service
public class CandidateServices implements ICandidateServices {
	
	@Autowired
	TokenUtil tokenutil;
	
	@Autowired
	CandidateRepository candidateRepo;

	@Override
	public Candidate addUser(String token, CandidateDTO user) throws LoginException {
		if(!token.isEmpty()) {
			Candidate hiredCandidate = new Candidate(user);
			return candidateRepo.save(hiredCandidate);
		}else {
			throw new LoginException("Access Denied...!");
		}
		
	}

	@Override
	public Candidate getProfile(String token, long id) throws LoginException, UserNotFoundException {
		if(token.isEmpty()) {
			throw new LoginException("Access Denied...!");
		}else {
			Optional<Candidate> isPresent = candidateRepo.findById(id);
			if(isPresent.isPresent()) {
				return isPresent.get();
			}else {
				throw new UserNotFoundException("User not present of this Id");
			}
			
		}
	}

	@Override
	public Candidate delete(String token, long id) throws LoginException, UserNotFoundException {
		if(this.getProfile(token, id) != null) {
			candidateRepo.delete(this.getProfile(token, id));
			return getProfile(token, id);
		}else {
			throw new UserNotFoundException("User not present of this Id");
		}
		
	}

	@Override
	public List<Candidate> getAllCandidates(String token) throws UserNotFoundException, LoginException {
		if(!token.isEmpty()) {
			List<Candidate> userData = new ArrayList<>();
			candidateRepo.findAll().forEach(userData::add);
			if(userData.isEmpty()) {
				throw new UserNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return userData;
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public Candidate editUser(String token, long id, CandidateDTO user) throws LoginException, UserNotFoundException {
		if(token.isEmpty()) {
			throw new LoginException("Access Denied...!");	
		}else {
			if(this.getProfile(token, id) != null) {
				Candidate hiredCandidate = this.getProfile(token, id);
				
				hiredCandidate.setFirstName(user.getFirstName());
				hiredCandidate.setLastName(user.getLastName());
				hiredCandidate.setMiddleName(user.getMiddleName());
				hiredCandidate.setEmail(user.getEmail());
				hiredCandidate.setMobile(user.getMobile());
				hiredCandidate.setHiredCity(user.getHiredCity());
				hiredCandidate.setHiredDate(user.getHiredDate());
				hiredCandidate.setDegree(user.getDegree());
				hiredCandidate.setAttitudeRemark(user.getAttitudeRemark());
				hiredCandidate.setKnowledgeRemark(user.getKnowledgeRemark());
				hiredCandidate.setOnBoardingStatus(user.getOnBoardingStatus());
				hiredCandidate.setStatus(user.getStatus());
				hiredCandidate.setJoindate(user.getJoindate());
				hiredCandidate.setLocation(user.getLocation());
				hiredCandidate.setAggrPer(user.getAggrPer());
				hiredCandidate.setCurrentPincode(user.getCurrentPincode());
				hiredCandidate.setPermanentPincode(user.getPermanentPincode());
				
				
				return candidateRepo.save(hiredCandidate);
				
			}else {
				throw new UserNotFoundException("User not present of this Id");
			}
		}
	}
	
	
}
