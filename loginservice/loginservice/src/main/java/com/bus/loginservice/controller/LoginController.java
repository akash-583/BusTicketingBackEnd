package com.bus.loginservice.controller;

import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.loginservice.jwt.JwtUtility;
import com.bus.loginservice.request.LoginRequest;
import com.bus.loginservice.response.JSONResponse;
import com.bus.loginservice.service.UserDetailsImpl;
import com.bus.loginservice.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registration")
public class LoginController {
	@Autowired
	DaoAuthenticationProvider authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtUtility jwtUtility;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		System.out.println("********************************************************* controller");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwtToken = jwtUtility.generateToken(authentication);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
	    List<String> collect = authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList());
	    JSONResponse jsonResponse = new JSONResponse(jwtToken, userDetails.getUsername(), collect);
		return ResponseEntity.ok(jsonResponse);
						
												
	}

}

