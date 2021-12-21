package com.bl.candidate.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bl.candidate.dto.CandidateDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Document(collection = "candidate")
public class Candidate {
	
	@Id
	private long candidateId;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobile;
	private String hiredCity;
	private String hiredDate;
	private String degree;
	private String attitudeRemark;
	private String knowledgeRemark;
	private String onBoardingStatus;
	private String status;
	private String creatorUser;
	private String joindate;
	private String location;
	private double aggrPer;
	private int currentPincode ;
	private int permanentPincode;
	
	public Candidate(CandidateDTO candidate) {
		this.candidateId =candidate.getId();
		this.firstName = candidate.getFirstName();
		this.middleName = candidate.getMiddleName();
		this.lastName = candidate.getLastName();
		this.email = candidate.getEmail();
		this.mobile = candidate.getMobile();
		this.hiredCity = candidate.getHiredCity();
		this.hiredDate = candidate.getHiredDate();
		this.degree = candidate.getDegree();
		this.attitudeRemark = candidate.getAttitudeRemark();
		this.knowledgeRemark = candidate.getKnowledgeRemark();
		this.onBoardingStatus = candidate.getOnBoardingStatus();
		this.status = candidate.getStatus();
		this.creatorUser =candidate.getCreatorUser();
		this.joindate = candidate.getJoindate();
		this.location = candidate.getLocation();
		this.aggrPer=candidate.getAggrPer();
		this.currentPincode =candidate.getCurrentPincode();
		this.permanentPincode =candidate.getPermanentPincode();
	}

}
