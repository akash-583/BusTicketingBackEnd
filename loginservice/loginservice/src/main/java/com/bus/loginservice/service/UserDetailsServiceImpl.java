package com.bus.loginservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.loginservice.model.Login;
import com.bus.loginservice.repository.LoginRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Login user = userRepository.findByUsername(username);
		if (user == null) {
			System.out.println("************************************************************** service Implelemtaion calss");
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		return UserDetailsImpl.getUser(user);
	}

}
