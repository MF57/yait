package edu.agh.yait.mailer;

import edu.agh.yait.persistence.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailerService")
public class Mailer {
    /**
    * Usage example
    *
    *
        private static Mailer mailer;
        @Autowired
        public void setMailer(Mailer mailer) {
            YaitApplication.mailer = mailer;
        }
        mailer.sendMail(String mailAddress, Ticket ticket);
    */

    private final TicketManager ticketManager;
    private final SenderService senderService;

    @Autowired
    public Mailer(TicketManager ticketManager, SenderService senderService) {
        this.ticketManager = ticketManager;
        this.senderService = senderService;
    }

    public void sendMail(String recipientMail, Ticket ticket) {
        TemplateMessageBuilder templateMessageBuilder = new TemplateMessageBuilder();
        MimeMessagePreparator preparator = templateMessageBuilder
                .constructMessagePreparator(recipientMail, ticketManager.getTokenUrl(ticket));

        senderService.sendMail(preparator);
    }
}
