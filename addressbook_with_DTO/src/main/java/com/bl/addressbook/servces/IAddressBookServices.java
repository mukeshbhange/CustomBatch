package com.bl.addressbook.servces;

import java.util.List;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.UserData;

public interface IAddressBookServices {

	UserData deleteUser(String token) throws UserNotFoundException;

	List<UserData> getAllUsers();

	UserData getOne(String token) throws UserNotFoundException;

	UserData updateUser(String token, UserData userData) throws UserNotFoundException;

	UserData addUserData(UserDTO user);

}
