package edu.agh.yait.mailer;

public class MailType {
    private String subject = "";
    private String senderName = "";
    private RecipientInfo recipientInfo;
    private String tokenUrl;

    public MailType(RecipientInfo recipientInfo, String tokenUrl) {
        this.recipientInfo = recipientInfo;
        this.tokenUrl = tokenUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSubject() {
        return subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public RecipientInfo getRecipientInfo() {
        return recipientInfo;
    }

    @Override
    public String toString() {
        return "EmailType: [subject = " + getSubject() + ", recipientInfo = " + getRecipientInfo().toString() + " ]";
    }
}
