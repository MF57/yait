package edu.agh.yait.mailer.service;

import edu.agh.yait.mailer.model.EmailType;

/**
 * Created by marcinsendera on 16.05.2017.
 */
public interface SenderService {
    public void sendConfirmationMail(EmailType emailType);
}
