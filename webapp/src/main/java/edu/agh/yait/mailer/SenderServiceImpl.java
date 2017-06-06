package edu.agh.yait.mailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("senderService")
public class SenderServiceImpl implements SenderService {
    private static final Logger logger = LoggerFactory.getLogger(SenderServiceImpl.class);

    private final JavaMailSender mailSender;

    @Autowired
    public SenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(MimeMessagePreparator preparator) {
        try {
            mailSender.send(preparator);
            logger.info("Message has been sent.............................");
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        }
    }
}
