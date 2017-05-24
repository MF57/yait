package edu.agh.yait.controllers;

import edu.agh.yait.dto.TokenRequestDTO;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.IssueStatus;
import edu.agh.yait.persistence.model.Ticket;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.persistence.repositories.TicketRepository;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Krzysztof Podsiadlo on 23.05.17.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    IssueRepository issueRepository;
    TicketRepository ticketRepository;

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

    @RequestMapping(value = "/generate_tokens", method = RequestMethod.POST)
    public Object generateTokens(@Valid @RequestBody TokenRequestDTO request, Errors result) {

        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        List<String> emails = request.getEmails();
        List<String> ldapGroups = request.getLdapGroups();
        Integer tokenPoints = request.getTokenPoints();
        Date expirationDate = request.getExpires_at();

        List<Ticket> tokens = new LinkedList<Ticket>();
        for(String email: emails) {
            Ticket ticket = new Ticket();
            ticket.setCreationDate(new Date());
            ticket.setPoints(tokenPoints);
            ticket.setExpirationDate(expirationDate);

            // generate token
            // send mail
        }

        return ticketRepository.save(tokens);
    }

    @RequestMapping(value = "/ldapGroups", method = RequestMethod.GET)
    public Object getLdapGroups(@Valid @RequestBody List<String> ldapGroups, Errors result) {

        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return "OK";
    }
}