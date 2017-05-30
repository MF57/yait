package edu.agh.yait.mailer;

import edu.agh.yait.persistence.model.Ticket;

public class TokenStatus {

    private Ticket ticket;
    private TokenStatusType type;

    public TokenStatus(Ticket ticket, TokenStatusType type) {
        this.ticket = ticket;
        this.type = type;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TokenStatusType getType() {
        return type;
    }

    public void setType(TokenStatusType type) {
        this.type = type;
    }
}
