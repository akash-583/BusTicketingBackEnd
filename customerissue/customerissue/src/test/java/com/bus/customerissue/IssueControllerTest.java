package com.bus.customerissue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bus.customerissue.controller.CustomerIssueController;
import com.bus.customerissue.exception.CustomerIssueException;
import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.model.LoginModel;
import com.bus.customerissue.model.UserIssueVO;
import com.bus.customerissue.service.CustomerIssueService;


@SpringBootTest
public class IssueControllerTest {
	
	 @InjectMocks
	    private CustomerIssueController issueController;

	    @Mock
	    private CustomerIssueService issueService;

	    @Test
	    public void testAddIssueWithValidInput() {
	       CustomerIssueModel CustomerIssueModel = new CustomerIssueModel();
	        CustomerIssueModel.setIssue("new issue");

	        when(issueService.addissue(CustomerIssueModel)).thenReturn("Apologies for hearing the Issue. Our Admin will look into these");

	        ResponseEntity<String> response = issueController.addissue(CustomerIssueModel);
	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals("Apologies for hearing the Issue. Our Admin will look into these", response.getBody());
	    }

	      @Test
	    public void testUpdateIssueWithValidInput() {
		    CustomerIssueModel CustomerIssueModel = new CustomerIssueModel();
	        CustomerIssueModel.setIssue("Test issue");
	        String username = "testUser";

	        when(issueService.updateIssue(CustomerIssueModel, username)).thenReturn(CustomerIssueModel);

	        ResponseEntity<CustomerIssueModel> response = issueController.updateIssue(CustomerIssueModel, username);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(CustomerIssueModel, response.getBody());
	    }
	      
	      @Test
	      public void testGetAllIssuesEmpty() {
	          when(issueService.getAllIssues()).thenThrow(new CustomerIssueException("Data is not Found"));

	          CustomerIssueException exception = assertThrows(CustomerIssueException.class, () -> issueController.getAllIssues());
	          assertEquals("Data is not Found", exception.getMessage());
	          verify(issueService, times(1)).getAllIssues();
	      }

	      @Test
	      public void testGetAllIssues_Success() {
	          // Create a list of CustomerIssueModel objects for testing
	          List<CustomerIssueModel> helpList = new ArrayList<>();
	          CustomerIssueModel issue1 = new CustomerIssueModel();
	          issue1.setUsername("user1");
	          issue1.setIssue("Issue 1");
	          issue1.setStatus("Resolved");
	          issue1.setSolution("Solution 1");

	          CustomerIssueModel issue2 = new CustomerIssueModel();
	          issue2.setUsername("user2");
	          issue2.setIssue("Issue 2");
	          issue2.setStatus("Processing");
	          issue2.setSolution("Solution 2");

	          helpList.add(issue1);
	          helpList.add(issue2);

	          when(issueService.getAllIssues()).thenReturn(helpList);

	      
	          ResponseEntity<List<CustomerIssueModel>> responseEntity = issueController.getAllIssues();

	          // Assert that the response is not null
	          assertNotNull(responseEntity);
	          assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	          // Assert that the response body contains the list of CustomerIssueModel objects
	          List<CustomerIssueModel> responseBody = responseEntity.getBody();
	          assertNotNull(responseBody);
	          assertEquals(2, responseBody.size());

	      }
	      
	      @Test
	      public void testGetByUsername() {
	          String username = "user1";

	          LoginModel loginModel = new LoginModel();
	          loginModel.setUsername(username);
	       
	          List<CustomerIssueModel> CustomerIssueModels = new ArrayList<>();
	         
	          UserIssueVO userIssueVO = new UserIssueVO();
	          userIssueVO.setLoginModel(loginModel);
	          userIssueVO.setCustomerIssueModel(CustomerIssueModels);        
	          when(issueService.getByUsername(username)).thenReturn(userIssueVO);
	         
	          ResponseEntity<UserIssueVO> responseEntity = issueController.getByUsername(username);
	          assertNotNull(responseEntity);

	          assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	      }
	      
	      @Test
	      public void testUpdateIssue_Success() {
	          CustomerIssueModel CustomerIssueModel = new CustomerIssueModel();
	          String issueId = "issue123";
	          when(issueService.updateIssue(CustomerIssueModel, issueId)).thenReturn(CustomerIssueModel);
	          ResponseEntity<CustomerIssueModel> responseEntity = issueController.updateIssue(CustomerIssueModel, issueId);
	          assertNotNull(responseEntity);
	          assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
	      }
	     

}

