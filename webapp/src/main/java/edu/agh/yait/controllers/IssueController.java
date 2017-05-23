package edu.agh.yait.controllers;

import edu.agh.yait.dto.IssueDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getIssues(){
        return issueRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addIssue(@Valid @RequestBody IssueDTO issueDTO,
                           Errors result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Issue issue = new Issue();
        issue.setTitle(issueDTO.getTitle());
        issue.setDescription(issueDTO.getDescription());
        issue.setPoints(0);
        issue.setStatus(IssueStatus.OPEN);

        return issueRepository.save(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public Object getIssueById(@PathVariable("issueId") String issueId){
        return issueRepository.findOne(Integer.parseInt(issueId));
    }
}
