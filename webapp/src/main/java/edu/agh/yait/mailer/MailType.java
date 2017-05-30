package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */
public class MailType {


    private String subject = "";

    private String senderName = "";

    private RecipientInfo recipientInfo;

    public MailType(RecipientInfo recipientInfo){
        this.recipientInfo = recipientInfo;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setSenderName(String senderName){
        this.senderName = senderName;
    }

    public String getSubject(){
        return subject;
    }

    public String getSenderName(){
        return senderName;
    }

    public RecipientInfo getRecipientInfo(){
        return recipientInfo;
    }

    @Override
    public String toString(){
        return "EmailType: [subject = "+getSubject()+", recipientInfo = "+getRecipientInfo().toString()+" ]";
    }
}
