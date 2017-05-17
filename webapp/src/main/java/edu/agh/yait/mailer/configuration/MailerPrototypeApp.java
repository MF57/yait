package edu.agh.yait.mailer.configuration;

/**
 * Created by marcinsendera on 16.05.2017.
 */
import edu.agh.yait.mailer.model.EmailType;
import edu.agh.yait.mailer.model.RecipientInfo;
import edu.agh.yait.mailer.service.SenderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MailerPrototypeApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SenderService senderService = (SenderService) context.getBean("senderService");
        senderService.sendConfirmationMail(getTestEmailToPeterAndMartin());
        ((AbstractApplicationContext) context).close();
    }

    public static EmailType getTestEmail(){
        String[] emailList = {"marcin.sendera@gmail.com"};
        RecipientInfo recipientInfo = new RecipientInfo("prototypeEmailList", emailList);
        EmailType emailType = new EmailType("333", "onlyTestEmail", recipientInfo);
        return emailType;
    }

    public static EmailType getTestEmailToPeterAndMartin(){
        String[] emailList = {"piotr@roksela.pl", "marcin.sendera@gmail.com"};
        RecipientInfo recipientInfo = new RecipientInfo("Piotr i Marcin", emailList);
        EmailType emailType = new EmailType("333", "onlyTestEmail", recipientInfo);
        return emailType;
    }

}
