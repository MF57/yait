package edu.agh.yait.controllers;

import edu.agh.yait.dto.VoteDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/issues/{issueId}/vote")
public class VoteController {

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Object voteIssue(@PathVariable("issueId") String issueId,
                            @Valid @RequestBody VoteDTO voteDTO,
                            Errors result){

        if(result.hasErrors()){
            return result.getAllErrors();
        }

        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        if(issue == null){
            return ResponseEntity.badRequest().body(new CustomErrorObject("Issue, id: " + issueId +  ", does not exists"));
        }

        issue.setPoints(issue.getPoints() + voteDTO.getPoints());

        return issueRepository.save(issue);
    }
}
