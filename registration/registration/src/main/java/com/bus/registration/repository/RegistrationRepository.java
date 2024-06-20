package com.bus.registration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bus.registration.model.Registration;

public interface RegistrationRepository extends MongoRepository<Registration,String>{

	Registration findByUsername(String username);
}
