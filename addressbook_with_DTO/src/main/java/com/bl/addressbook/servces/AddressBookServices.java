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
	
	private long user_id = 101;
	private String email = "bhangemukesh98@gmail.com";
	private String password = "Mukesh@000";
	
	@Autowired
	JwtUtil tokenutil;
	
	@Autowired
	UserDataRepo userRepo;
	
	@Override
	public UserData addUserData(String loginToken,UserDTO userDTO) {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			UserData userData = new UserData(userDTO);
			Address address = userData.getAddress();
			address.setUser(userData);
			userData.setAddress(address);
			userRepo.save(userData);
			return userData;
		}
		return null;
	}


	@Override
	public UserData deleteUser(String loginToken,long id) throws UserNotFoundException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			if(!userRepo.findById(id).isEmpty()) {
				UserData deleted = getOne(loginToken,id);
				userRepo.deleteById(id);
				return deleted;
			}else {
				throw new AddressBookExceptions(" "+id+" Id is not Present");
			}
		}
		return null;
	}

	@Override
	public List<UserData> getAllUsers(String loginToken) {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			List<UserData> userData = new ArrayList<>();
			userRepo.findAll().forEach(userData::add);
			if(userData.isEmpty()) {
				throw new AddressBookExceptions("No Data Present in Database,First Add Data");
			}else {
				return userData;
			}
		}
		return null;
	}

	@Override
	public UserData getOne(String loginToken,long id) throws UserNotFoundException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			if(userRepo.findById(id).isEmpty()) {
				throw new AddressBookExceptions(" "+id+" Id is not Present");
			}else {
				return userRepo.findById(id).get();

			}	
		}
		return null;
	}


	@Override
	public UserData updateUser(String loginToken,long id, UserData userData) throws UserNotFoundException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			UserData found_user = this.getOne( loginToken,id);

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
				throw new AddressBookExceptions(" "+id+" Id is not Present");
			}
		}
		return userData;
	}


	@Override
	public List<UserData> usersByCity(String loginToken,String city) {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			List<UserData> userByCityList =  userRepo.usersByCity(city);

			if(userByCityList.isEmpty()) {
				throw new AddressBookExceptions("No User Present in "+city+"city");
			}else {
				return userByCityList;
			}
		}
		return null;
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
		if (email.equals(this.email)) {
			if (password.equals(this.password)) {
				return tokenutil.createToken(this.user_id);
			} else {
				throw new UserNotFoundException(500,"Wrong Password!");
			}
		} else {
			throw new UserNotFoundException(404, "User not exist");
		}
	}
}
