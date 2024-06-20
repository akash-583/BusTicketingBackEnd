package com.bus.customerissue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.model.UserIssueVO;
import com.bus.customerissue.service.CustomerIssueService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/issue")
public class CustomerIssueController {
	@Autowired
	private CustomerIssueService customerIssueService;
	
	@PostMapping("/addIssue")
    public ResponseEntity<String> addissue(@RequestBody CustomerIssueModel customerIssueModel) {
		customerIssueService.addissue(customerIssueModel);
		return ResponseEntity.ok("Apologies for hearing the Issue. Our Admin will look into these");
	}
	
	@GetMapping("/getAllIssues")
	public ResponseEntity<List<CustomerIssueModel>> getAllIssues(){
		return ResponseEntity.ok(customerIssueService.getAllIssues());
	}
	
	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<UserIssueVO> getByUsername(@PathVariable String username) {
		return ResponseEntity.ok(customerIssueService.getByUsername(username));
	}
	
	@PutMapping("/update/{issue}")
	public ResponseEntity<CustomerIssueModel> updateIssue(@RequestBody CustomerIssueModel customerIssueModel, @PathVariable String issue) {
		return ResponseEntity.ok(customerIssueService.updateIssue(customerIssueModel, issue));
	}
	
	@GetMapping("/getUserissues/{username}")
	public List<CustomerIssueModel> getUserissues(@PathVariable String username) {
		return customerIssueService.getUserissues(username);
	}


}
