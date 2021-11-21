package com.bl.employeepayroll.model;

import com.bl.employeepayroll.dto.EmployeeDTO;

import lombok.Data;

@Data
public class EmployeePayrollData {
	
	private int employeeId;
	private String name;
	private long salary;
	
	public EmployeePayrollData(int id,EmployeeDTO empdto) {
		this.employeeId = id;
		this.name = empdto.getName();
		this.salary = empdto.getSalary();
	}

}
