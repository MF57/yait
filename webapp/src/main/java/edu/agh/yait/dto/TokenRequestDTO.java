package edu.agh.yait.dto;


import java.util.Date;
import java.util.List;

public class TokenRequestDTO {

    private List<String> emails;
    private List<String> ldapGroups;
    private Integer tokenPoints;
    private Date expires_at;

    public TokenRequestDTO() {
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getLdapGroups() {
        return ldapGroups;
    }

    public void setLdapGroups(List<String> ldapGroups) {
        this.ldapGroups = ldapGroups;
    }

    public Integer getTokenPoints() {
        return tokenPoints;
    }

    public void setTokenPoints(Integer tokenPoints) {
        this.tokenPoints = tokenPoints;
    }

    public Date getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Date expires_at) {
        this.expires_at = expires_at;
    }
}
