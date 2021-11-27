package com.bl.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exceptions.EmployeePayrollException;
import com.bl.employeepayroll.model.EmployeePayrollData;
import com.bl.employeepayroll.repository.IEmployeeRepo;
import com.bl.employeepayroll.util.TokenUtil;


@Service
public class EmployeeServices implements IEmployeeServices{
	
	@Autowired
	TokenUtil utilToken;
	
	@Autowired
	IEmployeeRepo employeeList;	
	
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		List<EmployeePayrollData> empList = new ArrayList<>();
		employeeList.findAll().forEach(empList::add);
		if(empList.isEmpty()) {
			throw new EmployeePayrollException("No Employee Present,First Add Employee Data");
		}
		return empList;
	}

	@Override
	public EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto) {
		if(employeeList.findByEmployeeByEmail(employeedto.getEmail()) != null) {
			EmployeePayrollData empData = null;
			empData = new EmployeePayrollData(employeedto);
			employeeList.save(empData);
			return empData;
			
		}else {
			throw new EmployeePayrollException(employeedto.getEmail()+" Email Already is already present");
		}
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(String token) {
		Optional<EmployeePayrollData> ispresent = employeeList.findById(utilToken.decodeToken(token));
		if(ispresent.isPresent()) {
			return ispresent.get();	
		}else {
			throw new EmployeePayrollException("Employee of Id "+utilToken.decodeToken(token)+" is not present");
		}
	}

	@Override
	public void deleteEmployeePayrollDataById(String token) {
		
		Optional<EmployeePayrollData> ispresent = employeeList.findById(utilToken.decodeToken(token));
		if(!ispresent.isEmpty()) {
			 	employeeList.deleteById(utilToken.decodeToken(token));

		}else {
			throw new EmployeePayrollException("Employee of Id "+utilToken.decodeToken(token)+" is not present");
			}
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollDataById(String token,EmployeeDTO employeedto) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(token);
		
		if(employeeList.findById(utilToken.decodeToken(token)).isEmpty()) {
			throw new EmployeePayrollException("Employee of Id "+utilToken.decodeToken(token)+" is not present");	
		}else {
			empData.setName(employeedto.getName());
			empData.setSalary(employeedto.getSalary());
			empData.setGender(employeedto.getGender());
			empData.setNote(employeedto.getNote());
			empData.setStartDate(employeedto.getStartDate());
			empData.setProfilePic(employeedto.getProfilePic());
			empData.setDepartments(employeedto.getDepartments());
			
			employeeList.save(empData);
			return empData;
		}
		
	}

	@Override
	public List<EmployeePayrollData> findByDepatment(String department) {
		List<EmployeePayrollData> empList = new ArrayList<>();
		if(empList.isEmpty()) {
			throw new EmployeePayrollException("No Employee in that Department");
		}else {
			employeeList.findByDepatment(department).forEach(empList::add);
			return empList;
		}
	}

	@Override
	public Object login(String email, String password) {
		EmployeePayrollData employee = employeeList.findByEmployeeByEmail(email);
		if(employee != null) {
			if(email.equals(employee.getPassword())) {
				return utilToken.createToken(employee.getEmployeeId());
			}else {
				throw new EmployeePayrollException("Password is Wrong ..plz Check");
			}
		}else {
			throw new EmployeePayrollException("This Email is Not present in EmployeePayroll");
		}
	}
}
