package com.bl.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exceptions.EmployeeNotFoundException;
import com.bl.employeepayroll.exceptions.EmployeePayRollExceptions;
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
		return empList;
	}

	@Override
	public EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(employeedto);
		employeeList.save(empData);
		return empData;
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(String token) {
		Optional<EmployeePayrollData> ispresent = employeeList.findById(utilToken.decodeToken(token));
		if(ispresent.isPresent()) {
			return ispresent.get();	
		}else {
			throw new EmployeePayRollExceptions("Employee of Id "+utilToken.decodeToken(token)+" not Found");
		}
	}

	@Override
	public void deleteEmployeePayrollDataById(String token) {
		
		Optional<EmployeePayrollData> ispresent = employeeList.findById(utilToken.decodeToken(token));
		if(ispresent.isPresent()) {
			 	employeeList.deleteById(utilToken.decodeToken(token));

		}else {
			throw new EmployeePayRollExceptions("Employee of Id "+utilToken.decodeToken(token)+" not Found");
		};
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollDataById(String token,EmployeeDTO employeedto) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(token);
		
		if(employeeList.findById(utilToken.decodeToken(token)).isEmpty()) {
			throw new EmployeeNotFoundException("Employee is not Found Of Id "+utilToken.decodeToken(token));	
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

	/*@Override
	public List<EmployeePayrollData> findEmployeeByDepatment(String department) {
		return employeeList.findEmployeeByDepatment(department) ;
	}*/
}
