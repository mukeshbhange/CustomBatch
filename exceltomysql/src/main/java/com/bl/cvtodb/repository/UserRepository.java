package com.bl.cvtodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.cvtodb.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
