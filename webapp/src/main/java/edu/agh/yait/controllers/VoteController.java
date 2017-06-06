package edu.agh.yait.controllers;

import edu.agh.yait.dto.VoteDTO;
import edu.agh.yait.mailer.TicketManager;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.model.Ticket;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.persistence.repositories.TicketRepository;
import edu.agh.yait.security.TokenAuthenticationService;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/issues/{issueId}/vote")
public class VoteController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketManager ticketManager;

    @RequestMapping(method = RequestMethod.POST)
    public Object voteIssue(@PathVariable("issueId") String issueId,
                            @Valid @RequestBody VoteDTO voteDTO,
                            @RequestHeader HttpHeaders headers,
                            Errors result) {

        String token = headers.get("Authorization").get(0);
        if (!TokenAuthenticationService.parseTokenType(token).equals("VOTE_TOKEN")) {
            return ResponseEntity.status(400).body(new CustomErrorObject("Wrong auth token type."));
        }

        if (result.hasErrors()) {
            return result.getAllErrors();
        }

        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        if (issue == null) {
            return ResponseEntity.badRequest().body(new CustomErrorObject("Issue, id: " + issueId + ", does not exists"));
        }

        if (issue.getStatus() != IssueStatus.open) {
            return ResponseEntity.status(400).body(new CustomErrorObject("Issue is not opened."));
        }

        Ticket ticket = ticketRepository.findOne(ticketManager.validateToken(token));
        if (ticket == null) {
            return ResponseEntity.status(400).body(new CustomErrorObject("Ticket not found."));
        }

        if (ticket.getPoints() < voteDTO.getPoints()) {
            return ResponseEntity.status(400).body(new CustomErrorObject("Not enough points left"));
        }

        ticket.setPoints(ticket.getPoints() - voteDTO.getPoints());
        issue.setScore(issue.getScore() + voteDTO.getPoints());

        ticketRepository.save(ticket);
        return issueRepository.save(issue);
    }
}
