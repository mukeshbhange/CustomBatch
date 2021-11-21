package com.bl.employeepayroll.services;

import java.util.List;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;

public interface IEmployeeServices {

	List<EmployeePayrollData> getEmployeePayrollData();

	EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto);

	EmployeePayrollData getEmployeePayrollDataById(int id);

	EmployeePayrollData deleteEmployeePayrollDataById(int id);

	EmployeePayrollData updateEmployeePayrollDataById(int id, EmployeeDTO employeedto);

}
