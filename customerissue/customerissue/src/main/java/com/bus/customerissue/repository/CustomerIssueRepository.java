package com.bus.customerissue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bus.customerissue.model.CustomerIssueModel;


@Repository
public interface CustomerIssueRepository extends MongoRepository<CustomerIssueModel, String>{

	CustomerIssueModel findByUsername(String username);

	CustomerIssueModel findByIssue(String issue);
	
	
}

