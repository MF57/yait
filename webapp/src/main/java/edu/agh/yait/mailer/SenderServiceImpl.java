package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("senderService")
public class SenderServiceImpl implements SenderService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendMail(MimeMessagePreparator preparator) {
        try {
            mailSender.send(preparator);
            System.out.println("Message has been sent.............................");
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
