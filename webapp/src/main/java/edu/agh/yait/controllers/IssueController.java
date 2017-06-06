package edu.agh.yait.controllers;

import edu.agh.yait.LdapFascade;
import edu.agh.yait.dto.IssueDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.model.User;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.persistence.repositories.UserRepository;
import edu.agh.yait.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {
    @Autowired
    private LdapFascade ldapFascade;

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getIssues() {
        Iterable<Issue> issues = issueRepository.findAll();
        issues.forEach(this::fetchIssueUserData);
        return issues;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addIssue(@Valid @RequestBody IssueDTO issueDTO,
                           @RequestHeader HttpHeaders header,
                           Errors result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Issue issue = new Issue();
        issue.setTitle(issueDTO.getTitle());
        issue.setDescription(issueDTO.getDescription());
        issue.setScore(0);
        issue.setStatus(IssueStatus.open);
        issue.setCreated_at(new Date());

        String token = header.get("Authorization").get(0);
        String userLdapId = TokenAuthenticationService.parseTokenLdapId(token);

        User user = new User();
        user.setLdapId(userLdapId);
        userRepository.save(user);
        user.fetchInformation(ldapFascade);
        issue.setAuthor(user);

        return issueRepository.save(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public Object getIssueById(@PathVariable("issueId") String issueId) {
        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        fetchIssueUserData(issue);
        issue.getComments().forEach(e -> e.getAuthor().fetchInformation(ldapFascade));
        return issue;
    }

    private void fetchIssueUserData(Issue issue) {
        if (issue.getAuthor() != null) {
            issue.getAuthor().fetchInformation(ldapFascade);
        }
        issue.getComments().forEach(comment -> comment.getAuthor().fetchInformation(ldapFascade));
    }
}
