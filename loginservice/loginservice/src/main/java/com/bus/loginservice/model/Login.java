package com.bus.loginservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="bususerlogin")
public class Login {
	
	@Id
	 private String username;
    private String password;
    private String role;
    private String email;
    private String gender;
    private Integer age;
    private String country;
}