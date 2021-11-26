package com.bl.employeepayroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bl.employeepayroll.model.EmployeePayrollData;



public interface IEmployeeRepo extends JpaRepository<EmployeePayrollData,Long>{
	
	@Query(value="Select * from employee_payroll_data,employee_departments where id = employee_id And department =:department",nativeQuery=true)
	List<EmployeePayrollData> findByDepatment(String department);
}
