package edu.agh.yait.controllers;

import edu.agh.yait.LdapHandler;
import edu.agh.yait.dto.IssueDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import edu.agh.yait.security.TokenAuthenticationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    @Autowired
    LdapHandler ldapHandler;

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getIssues(){
        return issueRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addIssue(@Valid @RequestBody IssueDTO issueDTO,
                           @RequestHeader HttpHeaders header,
                           Errors result){

        //System.out.println(header.get("Authorization"));
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        String token = header.get("Authorization").get(0);
        String userLdapId = TokenAuthenticationService.parseTokenLdapId(token);

        Issue issue = new Issue();
        issue.setAuthor(userLdapId);
        issue.setTitle(issueDTO.getTitle());
        issue.setDescription(issueDTO.getDescription());
        issue.setScore(0);
        issue.setStatus(IssueStatus.OPEN);

        return issueRepository.save(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public Object getIssueById(@PathVariable("issueId") String issueId){
        return issueRepository.findOne(Integer.parseInt(issueId));
    }
}
