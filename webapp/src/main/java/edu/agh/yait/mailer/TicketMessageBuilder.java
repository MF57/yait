package edu.agh.yait.mailer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketMessageBuilder extends TemplateMessageBuilder {

    private String subject;

    public TicketMessageBuilder(String subject, String templateDirectoryPath) {
        super(templateDirectoryPath);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    void sendTicket(String mailAddress, String url, int points, Date expirationDate) {
        Map<String, String> dict = new HashMap<>();
        dict.put("url", url);
        dict.put("points", String.valueOf(points));
        dict.put("expirationDate", expirationDate.toString());
        this.constructMessage(mailAddress, "ticketTemplate", this.getSubject(), dict);
    }
}
