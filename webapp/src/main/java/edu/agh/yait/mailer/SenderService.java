package edu.agh.yait.mailer;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface SenderService {
    void sendMail(MimeMessagePreparator preparator);
}
