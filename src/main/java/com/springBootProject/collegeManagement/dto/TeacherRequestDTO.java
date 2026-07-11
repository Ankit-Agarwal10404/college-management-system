package com.springBootProject.collegeManagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Email
	private String email;

	@Pattern(regexp = "\\d{10}")
	private String phone;

	@NotBlank
	private String department;

	@Positive
	private Double salary;

	@Min(0)
	private Integer experience;

}
