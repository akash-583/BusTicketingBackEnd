package com.bus.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.bus.registration.model.Registration;
import com.bus.registration.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	

	@PostMapping("/addUser")
	public ResponseEntity<?> saveUser(@Valid @RequestBody Registration user) throws Exception {
		if(user.getRole().equals("Admin")||user.getRole().equals("User")) {
			return ResponseEntity.ok(registrationService.save(user));
		}
		else
		{
			return new ResponseEntity<>("Please Select a valid role",
                    HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

}