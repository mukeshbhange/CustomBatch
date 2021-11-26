package com.bl.employeepayroll.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import com.bl.employeepayroll.dto.EmployeeDTO;
import lombok.Data;


@Entity
@Table
@Data
public class EmployeePayrollData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	
	private String name;
	private String gender;
	private String note;
	private LocalDate startDate;
	private String profilePic;
	private long salary;

	@ElementCollection
	@CollectionTable(name = "Employee_departments",joinColumns = @JoinColumn(name="id"))
	@Column(name="department")
	private List<String> departments;
	
	public EmployeePayrollData(EmployeeDTO empdto) {
		this.name = empdto.getName();
		this.salary = empdto.getSalary();
		this.gender = empdto.getGender();
		this.note = empdto.getNote();
		this.startDate = empdto.getStartDate();
		this.profilePic = empdto.getProfilePic();
		this.departments = empdto.getDepartments();
	}
	
	public EmployeePayrollData() {
		
	}
}
