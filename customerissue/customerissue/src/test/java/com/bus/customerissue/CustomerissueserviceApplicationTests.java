package com.bus.customerissue;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bus.customerissue.exception.CustomerIssueException;
import com.bus.customerissue.model.CustomerIssueModel;
import com.bus.customerissue.repository.CustomerIssueRepository;
import com.bus.customerissue.service.CustomerServiceImpl;



@SpringBootTest
class CustomerissueserviceApplicationTests {

    @InjectMocks
    private CustomerServiceImpl issueService;

    @Mock
    private CustomerIssueRepository issueRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddIssueWithValidInput() {
        CustomerIssueModel helpModel = new CustomerIssueModel();
        helpModel.setIssue("Test issue");
        helpModel.setStatus("New");
        helpModel.setSolution("In the progress");

        when(issueRepository.insert(helpModel)).thenReturn(helpModel);

        String response = issueService.addissue(helpModel);

        assertEquals("Apologies for hearing the Issue. Our Admin will look into these", response);
        verify(issueRepository, times(1)).insert(helpModel);
    }

    @Test
    public void testAddIssueWithEmptyIssue() {
        CustomerIssueModel helpModel = new CustomerIssueModel();
        helpModel.setIssue("");

        assertThrows(CustomerIssueException.class, () -> issueService.addissue(helpModel));
        verify(issueRepository, never()).insert(helpModel);
    }

    @Test
    public void testGetAllIssuesWithEmptyList() {
        when(issueRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(CustomerIssueException.class, () -> issueService.getAllIssues());
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateIssueWithNonExistingIssue() {
        String issue = "nonExistingIssue";
        CustomerIssueModel helpModel = new CustomerIssueModel();

        when(issueRepository.findByIssue(issue)).thenReturn(null);

        assertThrows(CustomerIssueException.class, () -> issueService.updateIssue(helpModel, issue));
        verify(issueRepository, never()).save(helpModel);
    }

    @Test
    public void testUpdateIssueWithExistingIssue() {
        String issue = "existingIssue";
        CustomerIssueModel existingIssueModel = new CustomerIssueModel();
        existingIssueModel.setIssue(issue);
        existingIssueModel.setStatus("New");
        existingIssueModel.setSolution("In the progress");

        CustomerIssueModel updatedIssueModel = new CustomerIssueModel();
        updatedIssueModel.setStatus("processing");
        updatedIssueModel.setSolution("In progress");

        when(issueRepository.findByIssue(issue)).thenReturn(existingIssueModel);

        CustomerIssueModel response = issueService.updateIssue(updatedIssueModel, issue);

        assertEquals("processing", response.getStatus());
        assertEquals("In progress", response.getSolution());
        verify(issueRepository, times(1)).save(existingIssueModel);
    }
}
