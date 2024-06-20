package com.bus.loginservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIssueModel {
	

	    private String issue;
		private String username;
		private String status;
		private String solution;

	}
