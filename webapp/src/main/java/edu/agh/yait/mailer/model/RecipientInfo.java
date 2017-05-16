package edu.agh.yait.mailer.model;

/**
 * Created by marcinsendera on 16.05.2017.
 */
public class RecipientInfo {

    private String contactName;

    private String[] emailAddresses;

    public RecipientInfo(String contactName, String[] emailAddresses){
        this.contactName = contactName;
        this.emailAddresses = emailAddresses;
    }

    public String getContactName(){
        return contactName;
    }

    public String[] getEmailAddresses(){
        return emailAddresses;
    }

    @Override
    public String toString(){
        return "RecipientInfo: [ contactName = "+getContactName()+"; emailAddresses = { " +getEmailAddresses()+" } ]";
    }

}
