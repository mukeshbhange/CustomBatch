package com.bl.onboarding.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.onboarding.model.Onboarding;


@Repository
public interface OnboardingRepo extends MongoRepository<Onboarding,Long > {
	
	
	@Query("{email: ?0}")
	Onboarding findByEmail(String email);

}
