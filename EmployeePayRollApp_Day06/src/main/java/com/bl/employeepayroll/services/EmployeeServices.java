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
			EmployeePayrollData empData = null;
			empData = new EmployeePayrollData(employeedto);
			employeeList.save(empData);
			return empData;
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(long id ) {
		Optional<EmployeePayrollData> ispresent = employeeList.findById(id);
		if(ispresent.isPresent()) {
			return ispresent.get();	
		}else {
			throw new EmployeePayrollException("Employee of Id "+id+" is not present");
		}
	}

	@Override
	public void deleteEmployeePayrollDataById(Long id) {
		
		Optional<EmployeePayrollData> ispresent = employeeList.findById(id);
		if(!ispresent.isEmpty()) {
			 	employeeList.deleteById(id);

		}else {
			throw new EmployeePayrollException("Employee of Id "+id+" is not present");
			}
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollDataById(long id,EmployeeDTO employeedto) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(id);
		
		if(employeeList.findById(id).isEmpty()) {
			throw new EmployeePayrollException("Employee of Id "+id+" is not present");	
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
