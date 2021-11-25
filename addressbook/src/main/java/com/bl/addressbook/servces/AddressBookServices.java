package com.bl.addressbook.servces;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.repository.UserDataRepo;
import com.bl.addressbook.util.JwtUtil;


@Service
public class AddressBookServices implements IAddressBookServices {
	
	@Autowired
	JwtUtil uToken;
	
	@Autowired
	UserDataRepo userRepo;
	
	@Override
	public UserData addUserData(UserData user) {
		Address address = user.getAddress();
		address.setUser(user);
		userRepo.save(user);
		return user;
	}


	@Override
	public UserData deleteUser(String token) throws UserNotFoundException {
		if(!userRepo.findById(uToken.decodeToken(token)).isEmpty()) {
			 UserData deleted = getOne(token);
			userRepo.deleteById(uToken.decodeToken(token));
			return deleted;
		}else {
			throw new UserNotFoundException((long)400,"Employee Not Found");
		}
	}

	@Override
	public List<UserData> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public UserData getOne(String token) throws UserNotFoundException {
		if(userRepo.findById(uToken.decodeToken(token)).isEmpty()) {
			throw new UserNotFoundException((long)400,"User Not Found");
		}else {
			return userRepo.findById(uToken.decodeToken(token)).get();
			
		}	
	}


	@Override
	public UserData updateUser(String token, UserData userData) throws UserNotFoundException {
		UserData found_user = this.getOne(token);
		if(found_user != null) {
			found_user.setName(userData.getName());
			found_user.setMobileNo(userData.getMobileNo());
			found_user.setEmail(userData.getEmail());
			found_user.setGender(userData.getGender());
			
			Long found_user_addr_id = found_user.getAddress().getId();
			Address address = new Address();
			
			address.setId(found_user_addr_id);
			address.setLandmark(userData.getAddress().getLandmark());
			address.setCity(userData.getAddress().getCity());
			address.setState(userData.getAddress().getState());
			address.setCountry(userData.getAddress().getCountry());
			address.setPinCode(userData.getAddress().getPinCode());
			address.setUser(found_user);
			found_user.setAddress(address);
			userRepo.save(found_user);
			
			return found_user;
		}else {
			new UserNotFoundException((long)400,"User Not Present to Update");
		}
		return null;
	}
}
