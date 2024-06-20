package com.bus.busservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bus.busservice.model.BusModel;



public interface BusRepository extends MongoRepository<BusModel, String>{

	List<BusModel> findBySourceAndDestination(String source,String destination);
	List<BusModel> findBySource(String source);
	BusModel findByBusNo(String busNo);
}

