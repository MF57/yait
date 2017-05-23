package edu.agh.yait.controllers;

import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Krzysztof Podsiadlo on 23.05.17.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(value = "issues/{issueId}/status", method = RequestMethod.PATCH)
    public Object addIssue(@PathVariable("issueId") String issueId, @RequestParam String status) {
        Issue issue = issueRepository.findOne(Integer.valueOf(issueId));
        IssueStatus newStatus = null;
        try {
            newStatus = IssueStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CustomErrorObject("Invalid status"));
        }
        issue.setStatus(newStatus);
        issueRepository.save(issue);
        return null;
    }
}
