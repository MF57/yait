package edu.agh.yait.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @RequestMapping(method = RequestMethod.GET)
    public Object getIssues(){
        return "list of issues";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addIssue(){
        return "issue added";
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public Object getIssueById(@PathVariable("issueId") String issueId){
        return "issue, id: " + issueId;
    }
}
