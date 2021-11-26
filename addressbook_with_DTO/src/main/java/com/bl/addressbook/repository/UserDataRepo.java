package com.bl.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.addressbook.model.UserData;


@Repository
public interface UserDataRepo extends JpaRepository<UserData, Long> {

}
