package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */
public class RecipientInfo {

    private String contactName;

    private String[] mailAddresses;

    public RecipientInfo(String contactName, String[] emailAddresses){
        this.contactName = contactName;
        this.mailAddresses = emailAddresses;
    }

    public String getContactName(){
        return contactName;
    }

    public String[] getMailAddresses(){
        return mailAddresses;
    }

    @Override
    public String toString(){
        return "RecipientInfo: [ contactName = "+getContactName()+"; emailAddresses = { " +getMailAddresses()+" } ]";
    }

}
