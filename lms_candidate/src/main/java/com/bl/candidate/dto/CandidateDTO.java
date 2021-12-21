package com.bl.candidate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateDTO {
	
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobile;
	private String hiredCity;
	
	
	@JsonFormat(pattern="dd MMM yyyy")
	private String hiredDate;
	private String degree;
	private String attitudeRemark;
	private String knowledgeRemark;
	private String onBoardingStatus;
	private String status;
	private String creatorUser;
	
	@JsonFormat(pattern="dd MMM yyyy")
	private String joindate;
	private String location;
	private double aggrPer;
	private int currentPincode ;
	private int permanentPincode;

}
