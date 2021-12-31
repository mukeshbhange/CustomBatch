package com.bl.employeepayroll.services;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;



public interface IEmployeeServices {

	List<EmployeePayrollData> getEmployeePayrollData();
	
	EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto);
	
	EmployeePayrollData getEmployeePayrollDataById(long id);
	
	void deleteEmployeePayrollDataById(Long id);
	
	EmployeePayrollData updateEmployeePayrollDataById(long id, EmployeeDTO employeedto);
	
	List<EmployeePayrollData> findByDepatment(String department);
	
	Object login(String email, String password);


}
