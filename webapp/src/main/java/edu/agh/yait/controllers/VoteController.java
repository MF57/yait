package edu.agh.yait.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue/{issueId}/vote")
public class VoteController {

    @RequestMapping(method = RequestMethod.POST)
    public Object voteIssue(@PathVariable("issueId") String issueId ){
        return "voted, issue: " + issueId;
    }
}
