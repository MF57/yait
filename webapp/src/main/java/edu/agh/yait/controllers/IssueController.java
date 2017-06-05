package edu.agh.yait.controllers;

import edu.agh.yait.LdapHandler;
import edu.agh.yait.dto.IssueDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.model.User;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.persistence.repositories.UserRepository;
import edu.agh.yait.security.TokenAuthenticationService;
import edu.agh.yait.userData.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private LdapHandler ldapHandler;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getIssues() {
        Iterable<Issue> issues = issueRepository.findAll();
        for (Issue issue : issues) {
            setIssueAuthor(issue);
        }
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
        issue.setAuthor(user);

        return issueRepository.save(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public Object getIssueById(@PathVariable("issueId") String issueId) {
        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        setIssueAuthor(issue);
        return issue;
    }

    private void setIssueAuthor(Issue issue) {
        if (issue.getAuthor() != null) {
            Optional<UserData> creator = ldapHandler.getUserDataById(issue.getAuthor().getLdapId());
            if (creator.isPresent()) {
                User user = new User();
                user.setLogin(creator.get().getLogin());
                user.setFirstName(creator.get().getName().orElse(null));
                user.setLastName(creator.get().getSurname().orElse(null));
                issue.setAuthor(user);
            }
        }
    }
}
