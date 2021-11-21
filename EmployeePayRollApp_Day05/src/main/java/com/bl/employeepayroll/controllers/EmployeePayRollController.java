package com.bl.employeepayroll.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.dto.ResponseDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;
import com.bl.employeepayroll.services.IEmployeeServices;

@RestController
@RequestMapping("/empservice")
public class EmployeePayRollController {
	
	
	@Autowired
	IEmployeeServices employeeServices;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeDTO employeedto) {
		EmployeePayrollData empData = null;
		empData = employeeServices.addEmployeePayrollData(employeedto);
		ResponseDTO resdto =new ResponseDTO("new Employee PayRoll Created Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<ResponseDTO> getEmployeeDataById(@PathVariable int id){
		EmployeePayrollData empData = null;
		empData = employeeServices.getEmployeePayrollDataById(id);
		ResponseDTO resdto =new ResponseDTO("Got data Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable int id) {
		employeeServices.deleteEmployeePayrollDataById(id);
		ResponseDTO resdto =new ResponseDTO("Deleted Successfully",id);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeDTO employeedto,@PathVariable int id) {
		EmployeePayrollData empData = null;
		empData = employeeServices.updateEmployeePayrollDataById(id,employeedto);
		ResponseDTO resdto =new ResponseDTO("Updated Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);
	}
	
	@RequestMapping("/getall")
	public ResponseEntity<ResponseDTO> getAllEmployeeData(){
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeeServices.getEmployeePayrollData();
		ResponseDTO resdto =new ResponseDTO("new Employee PayRoll Created Successfully",empDataList);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);		
	}
	
}
