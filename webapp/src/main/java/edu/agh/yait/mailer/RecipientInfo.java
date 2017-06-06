package edu.agh.yait.mailer;

public class RecipientInfo {

    private String contactName = "";
    private String mailAddress;

    public RecipientInfo(String emailAddress) {
        this.mailAddress = emailAddress;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    @Override
    public String toString() {
        return "RecipientInfo: [ contactName = " + getContactName() + "; emailAddresses = { " + getMailAddress() + " } ]";
    }

}
