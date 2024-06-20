package com.bus.loginservice.model;

import java.util.List;


import lombok.Data;

@Data
public class UserIssueVO {

	private Login loginModel;
	private List<CustomerIssueModel> helpModel;
}
