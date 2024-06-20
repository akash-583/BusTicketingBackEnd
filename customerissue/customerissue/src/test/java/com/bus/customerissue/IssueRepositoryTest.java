package com.bus.customerissue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.repository.CustomerIssueRepository;


@SpringBootTest
public class IssueRepositoryTest {
	 private CustomerIssueRepository issueRepository;

	    @BeforeEach
	    public void setUp() {
	        issueRepository = mock(CustomerIssueRepository.class);
	    }

	    @Test
	    public void testFindByUsername() {
	        String username = "user1";
	        CustomerIssueModel expectedHelp = new CustomerIssueModel();
	        expectedHelp.setUsername(username);

	        when(issueRepository.findByUsername(username)).thenReturn(expectedHelp);

	        CustomerIssueModel result = issueRepository.findByUsername(username);

	        assertNotNull(result);
	        assertEquals(username, result.getUsername());
	    }

	    @Test
	    public void testFindByIssue() {
	        String issue = "Test issue";
	        CustomerIssueModel expectedHelp = new CustomerIssueModel();
	        expectedHelp.setIssue(issue); 

	        when(issueRepository.findByIssue(issue)).thenReturn(expectedHelp);

	        CustomerIssueModel result = issueRepository.findByIssue(issue);

	        assertNotNull(result);
	        assertEquals(issue, result.getIssue());
	    }
}
