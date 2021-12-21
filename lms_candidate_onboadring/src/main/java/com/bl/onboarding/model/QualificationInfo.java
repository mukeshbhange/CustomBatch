package com.bl.onboarding.model;

import org.springframework.data.annotation.Id;

import com.bl.onboarding.dto.QualificationInfoDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QualificationInfo {
	
	private boolean diploma;
	private String degree;
	private String field;
	private String yearOfPassing;
	private String finalPercentage;
	private String aggrPercentage;
	private String enggCertificate;
	private String finalCertificate;
	private String trainingInstitute;
	private String trainingDuration;
	private String course;
		
	public QualificationInfo(QualificationInfoDTO qualificationInfo) {
		this.diploma = qualificationInfo.isDiploma();
		this.degree = qualificationInfo.getDegree();
		this.field = qualificationInfo.getField();
		this.yearOfPassing = qualificationInfo.getYearOfPassing();
		this.finalPercentage = qualificationInfo.getFinalPercentage();
		this.aggrPercentage = qualificationInfo.getAggrPercentage();
		this.enggCertificate = qualificationInfo.getEnggCertificate();
		this.finalCertificate = qualificationInfo.getFinalCertificate();
		this.trainingInstitute = qualificationInfo.getTrainingInstitute();
		this.trainingDuration = qualificationInfo.getTrainingDuration();
		this.course = qualificationInfo.getCourse();
	}

}
