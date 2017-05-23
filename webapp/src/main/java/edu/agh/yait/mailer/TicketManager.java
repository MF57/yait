package edu.agh.yait.mailer;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class TicketManager {

    private TicketMessageBuilder ticketMessageBuilder;

    public TicketManager() {
        this.ticketMessageBuilder = new TicketMessageBuilder("You've been granted ticket to vote", "/path/to/template/dir");
    }

    /**
     * Constructs email messages to {@code mailAddresses} informing about assigned points, their expiration date
     * and containing URL to access the website.
     * @param addressedTickets email address to Ticket mapping
     */
    public void sendTickets(Map<String, Ticket> addressedTickets) {
        Set<Ticket> tickets = new HashSet<>();
        for (Map.Entry<String, Ticket> entry : addressedTickets.entrySet()) {
            String email = entry.getKey();
            Ticket ticket = entry.getValue();
            // TODO: sendTicket should be creating mail message objects that can be sent by Mailer
            this.ticketMessageBuilder.sendTicket(email, ticket.getPoints(), ticket.getExpirationDate());
            // send mail using mailer
        }
    }

    /**
     * Validate string token
     * @param token
     * @return
     */
    public TokenStatus validateToken(String token) {
        // decode token to ticket
        // if cannot decode return null, INCORRECT
        // if ticket found but expired return null, EXPIRED
        // otherwise return ticket, VALID (doesn't matter if points are left or not)
        return new TokenStatus(null, TokenStatusType.INCORRECT);
    }

    public String generateToken(Ticket ticket) {
        String key = "secret";
        Map<String, Object> claims = new HashMap<>();
        claims.put("ticket", ticket.getIdentifier());
        String token = null;
        try {
            token = Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

}