package com.bus.customerissue.service;

import java.util.List;

import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.model.UserIssueVO;

public interface CustomerIssueService {

    public String addissue(CustomerIssueModel customerIssueModel);
	
	public List<CustomerIssueModel> getAllIssues();
	
	public UserIssueVO getByUsername(String username);
	
	public CustomerIssueModel updateIssue(CustomerIssueModel customerIssueModel, String issue);

	List<CustomerIssueModel> getUserissues(String username);
}
