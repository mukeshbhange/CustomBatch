package com.bl.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.lms.entity.LMS_User;


@Repository
public interface LMSRepository extends MongoRepository<LMS_User,Long>{
	
	
	@Query("{ 'emailId' : ?0 }")
	public Optional<LMS_User> findByEmailId(String emailId);
}
