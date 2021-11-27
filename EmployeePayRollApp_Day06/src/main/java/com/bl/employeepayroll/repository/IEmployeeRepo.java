package com.bl.employeepayroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bl.employeepayroll.model.EmployeePayrollData;



public interface IEmployeeRepo extends JpaRepository<EmployeePayrollData,Long>{
	
	@Query(value="Select * from employee_payroll_data,employee_departments where "
			+ "employee_payroll_data.employee_id = employee_departments.id And employee_departments.department = ?1"
			,nativeQuery=true)
	List<EmployeePayrollData> findByDepatment(String department);
	
	@Query(value="Select * from employee_payroll_data,employee_departments where"
			+ "employee_payroll_data.employee_id = employee_departments.id And employee_payroll_data.email = ?1"
			,nativeQuery=true)
	EmployeePayrollData findByEmployeeByEmail(String email);
}
