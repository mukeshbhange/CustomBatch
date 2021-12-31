package com.bl.onboarding.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bl.onboarding.dto.OnboardingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "onboarding")
public class Onboarding {
	
	@Id
	private long id;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNum;
	private String hiredCity;
	private String parentName;
	private String parentMobile;
	private String temporaryAddress;
	private String parentOccupation;
	private String parentAnnualSalary;
	private String permanentAddress;
	private String profileImage;
	private String folderId;
	private String status;
	
	private BankInfo bankInfo;
	private QualificationInfo qualificationInfo;
	
	
	public Onboarding(OnboardingDTO onboardingDTO) {
		this.id = onboardingDTO.getId();
		this.firstName = onboardingDTO.getFirstName();
		this.middleName = onboardingDTO.getMiddleName();
		this.lastName = onboardingDTO.getLastName();
		this.email = onboardingDTO.getEmail();
		this.mobileNum = onboardingDTO.getMobileNum();
		this.hiredCity = onboardingDTO.getHiredCity();
		this.parentName = onboardingDTO.getParentName();
		this.parentMobile = onboardingDTO.getParentMobile();
		this.parentAnnualSalary = onboardingDTO.getParentAnnualSalary();
		this.parentOccupation = onboardingDTO.getParentOccupation();
		this.temporaryAddress = onboardingDTO.getTemporaryAddress();
		this.permanentAddress = onboardingDTO.getPermanentAddress();
		this.profileImage = onboardingDTO.getProfileImage();
		this.folderId = onboardingDTO.getFolderId();
		this.status = onboardingDTO.getStatus();
		this.bankInfo = onboardingDTO.getBankInfo();
		this.qualificationInfo = onboardingDTO.getQualificationInfo();
		
	}

}
