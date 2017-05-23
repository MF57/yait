package edu.agh.yait.mailer;


import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Created by marcinsendera on 23.05.2017.
 */
public interface SenderService {
    public void sendMail(MimeMessagePreparator preparator);
}
