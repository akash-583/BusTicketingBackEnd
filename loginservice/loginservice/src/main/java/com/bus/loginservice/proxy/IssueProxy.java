package com.bus.loginservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bus.loginservice.model.CustomerIssueModel;
import com.bus.loginservice.model.UserIssueVO;

@FeignClient(name = "HELPSERVICE")
public interface IssueProxy {

    @PostMapping("/issue/addIssue")
    public String addissue(@RequestBody CustomerIssueModel customerIssueModel);

    @GetMapping("/issue/getAllIssues")
    public List<CustomerIssueModel> getAllIssues();

    @GetMapping("/issue/getByUsername/{username}")
    public UserIssueVO getByUsername(@PathVariable String username);


    @PutMapping("/issue/update/{issue}")
    public CustomerIssueModel updateIssue(@RequestBody CustomerIssueModel customerIssueModel, @PathVariable String issue);

    @GetMapping("issue/getUserissues/{username}")
    public List<CustomerIssueModel> getUserissues(@PathVariable String username);
}
