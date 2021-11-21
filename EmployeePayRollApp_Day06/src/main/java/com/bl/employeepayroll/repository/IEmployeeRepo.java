package com.bl.employeepayroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bl.employeepayroll.model.EmployeePayrollData;

public interface IEmployeeRepo extends JpaRepository<EmployeePayrollData,Long>{

}
