package com.bl.employeepayroll.dto;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {

	@Pattern( regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "First Letter of Name Must be capital")
	private String name;
	
	
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Email is not in proper format")
	private String email;
	
	@NotBlank(message="password cant be BLANK")
	private String password;
	
	@Min(value=500,message="cannot less than 500")
	private long salary;
	
	@Pattern(regexp="male|female",message="Gender eeds to be Male or female")
	private String gender;
	
	@NotBlank(message = "Note cannot be Empty")
	private String note;
	
	@JsonFormat(pattern = "dd MMM yyy")
	@PastOrPresent
	@NotNull
	private LocalDate startDate;
	
	@NotBlank(message="Profile Pic Cannot be Empty")
	private String profilePic;
	
	@NotNull
	private List<String> departments;
	
}
