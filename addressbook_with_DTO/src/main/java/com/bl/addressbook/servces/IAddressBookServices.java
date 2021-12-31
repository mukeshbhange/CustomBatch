package com.bl.addressbook.servces;

import java.util.List;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.UserData;

public interface IAddressBookServices {

	UserData deleteUser(String loginToken,long id) throws UserNotFoundException;

	List<UserData> getAllUsers(String loginToken);

	UserData getOne(String loginToken,long id) throws UserNotFoundException;

	UserData updateUser(String loginToken,long id, UserData userData) throws UserNotFoundException;

	UserData addUserData(String loginToken,UserDTO user);

	List<UserData> usersByCity(String loginToken,String city);

	List<UserData> allUsersSortedByName();

	Object loginToAddressBook(String email, String password);

}
