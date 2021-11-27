package com.bl.addressbook.servces;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exceptions.AddressBookExceptions;
import com.bl.addressbook.exceptions.UserNotFoundException;
import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.UserData;
import com.bl.addressbook.repository.UserDataRepo;
import com.bl.addressbook.util.JwtUtil;

@Validated
@Service
public class AddressBookServices implements IAddressBookServices {
	
	@Autowired
	JwtUtil uToken;
	
	@Autowired
	UserDataRepo userRepo;
	
	@Override
	public UserData addUserData(UserDTO userDTO) {
		if(userRepo.findByEmail(userDTO.email) == null) {
			throw new AddressBookExceptions(userDTO.email+" this email is already present,Please Use another one");
		}else {
			UserData userData = new UserData(userDTO);
			Address address = userData.getAddress();
			address.setUser(userData);
			userData.setAddress(address);

			userRepo.save(userData);
			return userData;
		}
	}


	@Override
	public UserData deleteUser(String token) throws UserNotFoundException {
		if(!userRepo.findById(uToken.decodeToken(token)).isEmpty()) {
			 UserData deleted = getOne(token);
			userRepo.deleteById(uToken.decodeToken(token));
			return deleted;
		}else {
			throw new AddressBookExceptions(" "+uToken.decodeToken(token)+" Id is not Present");
		}
	}

	@Override
	public List<UserData> getAllUsers() {
		
		List<UserData> userData = new ArrayList<>();
		userRepo.findAll().forEach(userData::add);
		if(userData.isEmpty()) {
			throw new AddressBookExceptions("No Data Present in Database,First Add Data");
		}else {
			return userData;
		}
	}

	@Override
	public UserData getOne(String token) throws UserNotFoundException {
		if(userRepo.findById(uToken.decodeToken(token)).isEmpty()) {
			throw new AddressBookExceptions(" "+uToken.decodeToken(token)+" Id is not Present");
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
			throw new AddressBookExceptions(" "+uToken.decodeToken(token)+" Id is not Present");
		}
	}


	@Override
	public List<UserData> usersByCity(String city) {
		List<UserData> userByCityList =  userRepo.usersByCity(city);
		
		if(userByCityList.isEmpty()) {
			throw new AddressBookExceptions("No User Present in "+city+"city");
		}else {
			return userByCityList;
		}
	}


	@Override
	public List<UserData> allUsersSortedByName() {
		List<UserData> sortedList = userRepo.findAll(Sort.by(Sort.Direction.ASC,"name"));
		if(sortedList.isEmpty()) {
			throw new AddressBookExceptions("No Data Present in Database,First Add Data");
		}else {
			return sortedList;
		}
	}


	@Override
	public Object loginToAddressBook(String email, String password) throws AddressBookExceptions {
		UserData user = userRepo.findByEmail(email);
		if(user != null) {
			if(password.equals(user.getPassword())) {
				return uToken.createToken(user.getUser_id());
			}else {
				throw new AddressBookExceptions("Password is Wrong for this User");
			}
		}else {
			throw new AddressBookExceptions("No Such Email present in AddressBook");
		}
	}
}
