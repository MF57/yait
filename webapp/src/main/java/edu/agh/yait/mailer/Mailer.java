package edu.agh.yait.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.util.HashMap;

public class Mailer {

/*
* Usage example
*
* String[] mailList = {"marcin.sendera@gmail.com"};
		RecipientInfo recipientInfo = new RecipientInfo("prototypeEmailList", mailList);
		Mailer mailer = new Mailer();
		mailer.sendMail(recipientInfo, "path to directory with ", "example_template.txt", "integracja", "kompoyait@gmail.com");

* */


    public void sendMail(RecipientInfo recipientInfo, String templateDirectoryPath, String templateName, String subject, String senderMail) {

        //TODO Think about getting context from webapp
        //TODO Think about taking properties like senderMail from application.properties
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailerConfig.class);

        TemplateMessageBuilder templateMessageBuilder = new TemplateMessageBuilder(templateDirectoryPath);

        MimeMessagePreparator preparator = templateMessageBuilder
                .constructMessagePreparator(recipientInfo.getMailAddresses(), templateName, subject, senderMail, new HashMap<String, String>());

        SenderService senderService = (SenderService) context.getBean("senderService");
        senderService.sendMail(preparator);
        ((AbstractApplicationContext) context).close();
    }

    // TODO: how to send emails? seperate thread and queueing?
}
