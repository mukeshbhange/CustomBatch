package com.bl.employeepayroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.bl.employeepayroll.dto.EmployeeDTO;

import lombok.Data;


@Entity
@Table
@Data
public class EmployeePayrollData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	
	
	@Pattern( regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Employee can not be null")
	private String name;
	
	@Min(value=500,message="cannot less than 500")
	private long salary;
	
	public EmployeePayrollData(EmployeeDTO empdto) {
		this.name = empdto.getName();
		this.salary = empdto.getSalary();
	}
	
	public EmployeePayrollData() {
		
	}

}
