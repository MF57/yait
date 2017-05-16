package edu.agh.yait.mailer;

public class Ticket {
    // TODO: use persistence model

    private String token = "1234567890";


    public String getIdentifier() {
        return token;
    }
}
