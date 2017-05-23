package edu.agh.yait.mailer;

import java.util.Date;

public class Ticket {
    // TODO: use persistence model

    private int id;
    private Date expirationDate;
    private Integer points;
    private String hash;


    public int getId() {
        return id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Integer getPoints() {
        return points;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTicketURL() {
        // TODO: url base for ticket addresses ??
        return "http://www.vote.iiet.pl/token/" + this.getHash() + "/";
    }


}
