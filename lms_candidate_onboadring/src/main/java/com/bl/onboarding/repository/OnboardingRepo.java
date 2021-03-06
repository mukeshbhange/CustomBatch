package com.bl.onboarding.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.onboarding.model.Onboarding;


@Repository
public interface OnboardingRepo extends JpaRepository<Onboarding,Long > {
	
	
	@Query(value="Select * from onboarding where email = ?1",nativeQuery=true)
	Onboarding findByEmail(String email);

}
