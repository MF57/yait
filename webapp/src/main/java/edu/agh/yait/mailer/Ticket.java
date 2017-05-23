package edu.agh.yait.mailer;

import java.util.Date;

public class Ticket {
    // TODO: use persistence model

    private String identifier = "1234567890";
    private Date expirationDate;
    private Integer points;
    private String hash;

    public String getIdentifier() {
        return identifier;
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

    public static String getTicketURL(String token) {
        // TODO: url base for ticket addresses ??
        return "http://www.vote.iiet.pl/token/" + token + "/";
    }


}
