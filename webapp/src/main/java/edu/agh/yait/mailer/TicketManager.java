package edu.agh.yait.mailer;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketManager {

    /**
     * Constructs email messages to {@code mailAddresses} informing about assigned points, their expiration date
     * and containing URL to access the website.
     * @param mailAddresses
     * @param points
     * @param expirationDate
     */
    public Set<Ticket> grantPoints(List<String> mailAddresses, int points, Date expirationDate) {
        // TODO: url base for ticket addresses ??
        Set<Ticket> tickets = new HashSet<>();
        for (String address : mailAddresses) {
            // create ticket with proper values
            // create token for ticket
            // create message from tokens mail template
            // send mail
            // return ticket object
            Ticket ticket = new Ticket();
            tickets.add(ticket);
        }
        return tickets;
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

}