package edu.agh.yait.controllers;

import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Podsiadlo on 23.05.17.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(value = "issues/{issueId}/status", method = RequestMethod.PATCH)
    public void addIssue(@PathVariable("issueId") String issueId) {
        Issue issue = issueRepository.findOne(Integer.valueOf(issueId));
        issue.changeStatus();
        issueRepository.save(issue);
    }
}
