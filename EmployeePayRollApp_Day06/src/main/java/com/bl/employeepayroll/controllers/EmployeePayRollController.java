package com.bl.employeepayroll.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.dto.ResponseDTO;
import com.bl.employeepayroll.model.EmployeePayrollData;
import com.bl.employeepayroll.services.IEmployeeServices;
import com.bl.employeepayroll.util.TokenUtil;


@CrossOrigin(allowedHeaders="*", origins="*")
@RestController
@RequestMapping("/empservices")
public class EmployeePayRollController {

	@Autowired
	TokenUtil tokenutil;

	@Autowired
	IEmployeeServices employeeServices;

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeedto) {
		EmployeePayrollData empData = null;
		empData = employeeServices.addEmployeePayrollData(employeedto);
		ResponseDTO resdto =new ResponseDTO("new Employee PayRoll Created Successfully",empData,tokenutil.createToken(empData.getEmployeeId()));
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.CREATED);	

	}

	@RequestMapping("/get/{id}")
	public ResponseEntity<ResponseDTO> getEmployeeDataById(@PathVariable long id){
		EmployeePayrollData empData = null;
		empData = employeeServices.getEmployeePayrollDataById(id);
		ResponseDTO resdto =new ResponseDTO("Got data Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);	
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable long id) {
		employeeServices.deleteEmployeePayrollDataById(id);
		ResponseDTO resdto =new ResponseDTO("Deleted Successfully ",(id));
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.ACCEPTED);	
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeedto,@PathVariable long id) {
		EmployeePayrollData empData = null;
		empData = employeeServices.updateEmployeePayrollDataById(id,employeedto);
		ResponseDTO resdto =new ResponseDTO("Updated Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);
	}

	@RequestMapping("/getall")
	public ResponseEntity<ResponseDTO> getAllEmployeeData(){
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeeServices.getEmployeePayrollData();
		ResponseDTO resdto =new ResponseDTO(" All Data got Successfully",empDataList);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);			
	}

	@RequestMapping("/get/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeeDataByDepartment(@PathVariable String department){
		List<EmployeePayrollData> empData = null;
		empData = employeeServices.findByDepatment(department);
		ResponseDTO resdto =new ResponseDTO("Got Departments Successfully",empData);
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);		
	}
	
	@RequestMapping("/login")
	public ResponseEntity<ResponseDTO> loginToEmployeePayroll(@RequestParam String email,@RequestParam String password) {
		ResponseDTO resdto =new ResponseDTO("LogIn Successfully ",employeeServices.login(email,password));
		return new ResponseEntity<ResponseDTO>(resdto,HttpStatus.OK);	
	}
}
