package com.bus.customerissue.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.customerissue.exception.CustomerIssueException;
import com.bus.customerissue.externalServices.LoginProxy;
import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.model.LoginModel;
import com.bus.customerissue.model.UserIssueVO;
import com.bus.customerissue.repository.CustomerIssueRepository;
@Service
public class CustomerServiceImpl implements CustomerIssueService{
	
	@Autowired
	private CustomerIssueRepository customerIssueRepository;
	
	@Autowired
	private LoginProxy loginProxy;
	
	
	
	@Override
	public String addissue(CustomerIssueModel customerIssueModel) {
		String issue = customerIssueModel.getIssue();
		customerIssueModel.setStatus("New");
		customerIssueModel.setSolution("In the progress");
		if (issue.isEmpty()) {
			throw new CustomerIssueException("Please provide the Issue.");
		} else {
			customerIssueRepository.insert(customerIssueModel);
			return "Apologies for hearing the Issue. Our Admin will look into these";
		}
	}

	@Override
	public List<CustomerIssueModel> getAllIssues() {
		    List<CustomerIssueModel> issues = customerIssueRepository.findAll();
		    
		    if (issues.isEmpty()) {
		        throw new CustomerIssueException("No issues found.");
		    }

		    return issues;
	}

	@Override
	public UserIssueVO getByUsername(String username) {
		List<CustomerIssueModel> list = new ArrayList<CustomerIssueModel>();
		List<CustomerIssueModel> all = customerIssueRepository.findAll();
		UserIssueVO userIssueVO = new UserIssueVO();

		for (CustomerIssueModel h : all) {
			String username2 = h.getUsername();

			if (username2.equals(username)) {
				list.add(h);
			}
		}

		if (!list.isEmpty()) {
			LoginModel userDetails = loginProxy.getbyUserName(username);
			userIssueVO.setCustomerIssueModel(list); // Set the list of matching HelpModels
			userIssueVO.setLoginModel(userDetails);
		}

		return userIssueVO;
	}

	@Override
	public CustomerIssueModel updateIssue(CustomerIssueModel customerIssueModel, String issue) {
		 CustomerIssueModel issueModel = customerIssueRepository.findByIssue(issue);
		    if (issueModel != null) {
		        String newStatus = customerIssueModel.getStatus();
		        if ("processing".equalsIgnoreCase(newStatus) || "resolved".equalsIgnoreCase(newStatus)) {
		            // Update the existing helpModel1 object instead of creating a new one
		            issueModel.setStatus(newStatus);
		            issueModel.setSolution(customerIssueModel.getSolution());
		            customerIssueRepository.save(issueModel);
		        } else {
		            throw new CustomerIssueException("Give Proper Status");
		        }
		        return issueModel;
		    } else {
		        throw new CustomerIssueException("There is nothing to update :)");
		    }
	}

	@Override
	public List<CustomerIssueModel> getUserissues(String username) {
		List<CustomerIssueModel> list = new ArrayList<CustomerIssueModel>();
		List<CustomerIssueModel> all = customerIssueRepository.findAll();
		for (CustomerIssueModel h : all) {
			String username2 = h.getUsername();
			System.out.println("Error Here ****************" + username2);

			if (username2.equals(username)) {
				list.add(h);
			}
		}
		return list;
	}

}
