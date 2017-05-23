package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */
public class MailType {

    private String mailID;

    private String type;

    private RecipientInfo recipientInfo;

    public MailType(String mailID, String type, RecipientInfo recipientInfo){
        this.mailID = mailID;
        this.type = type;
        this.recipientInfo = recipientInfo;
    }

    public String getMailID(){
        return mailID;
    }

    public String getType(){
        return type;
    }

    public RecipientInfo getRecipientInfo(){
        return recipientInfo;
    }

    @Override
    public String toString(){
        return "EmailType: [ mailID = "+getMailID()+", type = "+getType()+", recipientInfo = "+getRecipientInfo().toString()+" ]";
    }
}
