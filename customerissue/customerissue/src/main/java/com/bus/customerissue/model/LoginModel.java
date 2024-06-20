package com.bus.customerissue.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class LoginModel {
	
	@Id
	private String username;
	private String password;
	private String dateOfBirth;
	private String gender;
	private String location;
	private long mobileNumber;
	private String email;
	private String role;
}