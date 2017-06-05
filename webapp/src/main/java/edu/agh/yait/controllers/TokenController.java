package edu.agh.yait.controllers;

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

//    @Autowired //TODO: Odkomentowac kiedy bedzie mailer
//    private TicketManager ticketManager;

    @RequestMapping(method = RequestMethod.GET)
    public Object getTokenPointsLeft(@RequestHeader(value = "Authorization") String token,
                                     Errors result) {
        if (result.hasErrors()) {
            return result.getAllErrors();
        }

//        Ticket ticket = ticketRepository.findOne(TicketManager.validateToken(token)); //TODO: Podmienic kiedy bedzie mailer
        Ticket ticket = ticketRepository.findOne(1);

        System.out.println(TokenAuthenticationService.parseTokenType(token));

        if (ticket == null) {
            return ticket;
        }

        return ticket.getPoints();
    }
}
