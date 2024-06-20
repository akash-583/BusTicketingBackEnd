package com.bus.loginservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bus.loginservice.model.Login;



public interface LoginRepository extends MongoRepository<Login, String>{

	Login findByUsername(String username);
}

