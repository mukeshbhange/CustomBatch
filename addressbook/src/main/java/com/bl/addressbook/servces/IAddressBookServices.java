package com.bl.addressbook.servces;

import java.util.List;

import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.UserData;

public interface IAddressBookServices {

	UserData addUserData(UserData user);

	UserData deleteUser(String token) throws UserNotFoundException;

	List<UserData> getAllUsers();

	UserData getOne(String token) throws UserNotFoundException;

	UserData updateUser(String token, UserData userData) throws UserNotFoundException;

}
