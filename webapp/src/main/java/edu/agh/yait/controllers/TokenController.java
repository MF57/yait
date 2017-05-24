package edu.agh.yait.controllers;

import edu.agh.yait.persistence.model.Ticket;
import edu.agh.yait.persistence.repositories.TicketRepository;
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
    TicketRepository ticketRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getTokenPointsLeft(@RequestHeader(value="Authorization") String hash,
                                     Errors result){
        if(result.hasErrors()){
            return result.getAllErrors();
        }

        Ticket ticket = ticketRepository.findByHash(hash);

        if(ticket == null){
            return ticket;
        }

        return ticket;
    }
}
