package com.bl.addressbook.servces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.repository.UserDataRepo;


@Service
public class AddressBookServices implements IAddressBookServices {
	
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
	public void deleteUser(long id) {
		userRepo.deleteById(id);
	}


	@Override
	public List<UserData> getAllUsers() {
		return userRepo.findAll();
	}


	@Override
	public Optional<UserData> getOne(long id) {
		return userRepo.findById(id);
	}


	@Override
	public UserData updateUser(long id, UserData userData) {
		Optional<UserData> found = this.getOne(id);
		if(found.isPresent()) {
			found.get().setName(userData.getName());
			found.get().setMobileNo(userData.getMobileNo());
			found.get().setEmail(userData.getEmail());
			found.get().setGender(userData.getGender());
			
			Address addr = new Address();
			addr.setLandmark(userData.getAddress().getLandmark());
			addr.setCity(userData.getAddress().getCity());
			addr.setState(userData.getAddress().getState());
			addr.setCountry(userData.getAddress().getCountry());
			addr.setPinCode(userData.getAddress().getPinCode());
			addr.setUser(found.get());
			userRepo.save(found.get());
		}
		return null;
	}

}
