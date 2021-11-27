package com.bl.addressbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.addressbook.model.UserData;


@Repository
public interface UserDataRepo extends JpaRepository<UserData, Long> {
	
	
	@Query(value="SELECT * FROM user_data,address WHERE user_data.user_id = address.user_id "
			+ "AND address.city = ?1",nativeQuery = true)
	List<UserData> usersByCity(String city);
	
	@Query(value="select * from user_data,address where user_data.user_id = address.user_id"
			+ " AND user_data.email = ?1",nativeQuery = true)
	UserData findByEmail(String email);
	
}
