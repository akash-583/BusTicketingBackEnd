package com.bus.customerissue.model;

import java.util.List;


import lombok.Data;
@Data
public class UserIssueVO {

	private LoginModel loginModel;
	private List<CustomerIssueModel> customerIssueModel;
}
