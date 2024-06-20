package com.bus.loginservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.loginservice.exception.UserException;
import com.bus.loginservice.model.Login;
import com.bus.loginservice.repository.LoginRepository;

@Service
public class LoginServiceImpl {


	@Autowired
	private LoginRepository loginRepository;

	public List<Login> getAllUsers() {
		List<Login> list=new ArrayList<>();
		List<Login> findAll = loginRepository.findAll();
		for(Login login:findAll) {
			String role = login.getRole();
			if (role.equals("User")||role.equals("user")) {
			   list.add(login);
			}
		} 
		return list;
	}

	
	public Login getByUsername(String username) {
		Login login = loginRepository.findByUsername(username);
		if (login == null) {
			throw new UserException("No data is found by these username " + username);
		} else {
			return login;
		}
	}

	public Login updatePassword(String username, Login Login) {
		Login login = loginRepository.findByUsername(username);
		if (login == null) {
			throw new UserException("No data is found by these " + Login);
		} else {

			login.setPassword(Login.getPassword());
			Login updated = loginRepository.save(login);
			return updated;

		}
	}

	
	public Login updateByUserDetails(String username, Login Login) {
		Login login = loginRepository.findByUsername(username);
		if (login == null) {
			throw new UserException("No data is found by these " + Login);
		} else {

			login.setPassword(Login.getPassword());
			login.setEmail(Login.getEmail());
			login.setGender(Login.getGender());
			Login updated = loginRepository.save(login);
			return updated;

		}
	}
	
	public String DeleteUser(String username) {
		
		loginRepository.deleteById(username);
		
		return "User Deleted successfully";
	}
}
