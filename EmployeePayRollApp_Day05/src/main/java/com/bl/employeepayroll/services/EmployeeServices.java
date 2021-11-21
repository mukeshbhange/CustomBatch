package com.bl.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;


@Service
public class EmployeeServices implements IEmployeeServices{
	
	private List<EmployeePayrollData> employeeList = new ArrayList<>();
	
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeeList;
	}

	@Override
	public EmployeePayrollData addEmployeePayrollData(EmployeeDTO employeedto) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(employeeList.size()+1,employeedto);
		employeeList.add(empData);
		return empData;
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int id) {
		return employeeList.get(id-1);
	}

	@Override
	public EmployeePayrollData deleteEmployeePayrollDataById(int id) {
		return employeeList.remove(id-1);
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollDataById(int id,EmployeeDTO employeedto) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(id);
		empData.setName(employeedto.getName());
		empData.setSalary(employeedto.getSalary());
		employeeList.set(id-1, empData);
		return empData;
	}
}
