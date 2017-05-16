package edu.agh.yait.mailer.service;

import edu.agh.yait.mailer.model.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marcinsendera on 16.05.2017.
 */
@Service("senderService")
public class SenderServiceImpl implements SenderService {

    @Autowired
    MailService mailService;

    @Override
    public void sendConfirmationMail(EmailType emailType) {
        mailService.sendEmail(emailType);
    }
}
