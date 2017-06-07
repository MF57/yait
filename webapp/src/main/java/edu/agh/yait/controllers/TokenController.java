package edu.agh.yait.controllers;

import edu.agh.yait.mailer.TicketManager;
import edu.agh.yait.persistence.model.Ticket;
import edu.agh.yait.persistence.repositories.TicketRepository;
import edu.agh.yait.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/token")
public class TokenController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketManager ticketManager;

    @RequestMapping(method = RequestMethod.GET)
    public Object getTokenPointsLeft(@RequestHeader(value = "Authorization") String token,
                                     Errors result) {
        if (result.hasErrors()) {
            return result.getAllErrors();
        }

        Ticket ticket = ticketRepository.findOne(ticketManager.validateToken(token));

        System.out.println(TokenAuthenticationService.parseTokenType(token));

        if (ticket == null) {
            return ticket;
        }

        return ticket.getPoints();
    }
}
