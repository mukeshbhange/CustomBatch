package com.bl.addressbook.servces;

import java.util.List;
import java.util.Optional;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.UserData;

public interface IAddressBookServices {

	UserData addUserData(UserData user);

	void deleteUser(long id);

	List<UserData> getAllUsers();

	Optional<UserData> getOne(long id);

	UserData updateUser(long id, UserData userData);

}
