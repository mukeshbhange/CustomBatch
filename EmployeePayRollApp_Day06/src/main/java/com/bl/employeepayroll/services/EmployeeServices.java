package com.bl.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;
import com.bl.employeepayroll.repository.IEmployeeRepo;


@Service
public class EmployeeServices implements IEmployeeServices{
	
	@Autowired
	IEmployeeRepo employeeList;
	
	//private List<EmployeePayrollData> employeeList = new ArrayList<>();
	
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
	public EmployeePayrollData getEmployeePayrollDataById(long id) {
		Optional<EmployeePayrollData> ispresent = employeeList.findById(id);
		if(ispresent.isPresent()) {
			return ispresent.get();	
		}
		return null;
	}

	@Override
	public void deleteEmployeePayrollDataById(long id) {
		employeeList.deleteById(id);
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollDataById(long id,EmployeeDTO employeedto) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(id);
		empData.setName(employeedto.getName());
		empData.setSalary(employeedto.getSalary());
		employeeList.save(empData);
		return empData;
	}
}
