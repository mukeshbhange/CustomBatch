package com.bl.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.lms.entity.LMS_User;


@Repository
public interface LMSRepository extends JpaRepository<LMS_User,Long>{
	
	public Optional<LMS_User> findByEmailId(String emailId);
}
