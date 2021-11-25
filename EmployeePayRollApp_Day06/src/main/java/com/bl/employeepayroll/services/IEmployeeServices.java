package com.bl.employeepayroll.services;

import java.util.List;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;

public interface IEmployeeServices {

	List<EmployeePayrollData> getEmployeePayrollData();
	EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto);
	EmployeePayrollData getEmployeePayrollDataById(String token);
	void deleteEmployeePayrollDataById(String token);
	EmployeePayrollData updateEmployeePayrollDataById(String token, EmployeeDTO employeedto);
	
	//List<EmployeePayrollData> findEmployeeByDepatment(String department);


}
