package com.bus.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bus.registration.exception.RegistrationException;
import com.bus.registration.model.Registration;
import com.bus.registration.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	
	@Override
	public Registration save(Registration user) {
		// TODO Auto-generated method stub
		Registration exUser=registrationRepository.findByUsername(user.getUsername());
		
		if(exUser!=null)
		{
			throw new RegistrationException("The username "+user.getUsername()+" already exists");
		}
		Registration login = new Registration();
		login.setUsername(user.getUsername());
		login.setPassword(bcryptEncoder.encode(user.getPassword()));
		login.setRole(user.getRole());
		login.setGender(user.getGender());
		login.setEmail(user.getEmail());
		login.setCountry(user.getCountry());
		login.setAge(user.getAge());
		return registrationRepository.save(login);
		
	}

}
