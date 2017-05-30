package edu.agh.yait.mailer;

import edu.agh.yait.persistence.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailerService")
public class Mailer {
/*
* Usage example
*
* String[] mailList = {"marcin.sendera@gmail.com"};
		RecipientInfo recipientInfo = new RecipientInfo("prototypeEmailList", mailList);
		Mailer mailer = new Mailer();
		mailer.sendMail(recipientInfo, "whatever", "example_template.txt", ticket);

* */
    private final TicketManager ticketManager;
    private final SenderService senderService;

    @Autowired
    public Mailer(TicketManager ticketManager, SenderService senderService) {
        this.ticketManager = ticketManager;
        this.senderService = senderService;
    }

    public void sendMail(RecipientInfo recipientInfo, String templateDirectoryPath, String templateName, Ticket ticket) {
        //TODO Think about taking properties like senderMail from application.properties

        TemplateMessageBuilder templateMessageBuilder = new TemplateMessageBuilder(templateDirectoryPath);
        MimeMessagePreparator preparator = templateMessageBuilder
                .constructMessagePreparator(recipientInfo.getMailAddresses(), templateName, ticketManager.getTokenUrl(ticket));

        senderService.sendMail(preparator);
    }
}
