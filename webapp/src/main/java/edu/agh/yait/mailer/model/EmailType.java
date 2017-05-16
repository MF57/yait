package edu.agh.yait.mailer.model;

/**
 * Created by marcinsendera on 16.05.2017.
 */
public class EmailType {

    private String mailID;

    private String type;

    private RecipientInfo recipientInfo;

    public EmailType(String mailID, String type, RecipientInfo recipientInfo){
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
