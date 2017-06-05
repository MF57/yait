package edu.agh.yait.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class TokenRequestDTO {
    @NotEmpty
    private List<String> emails;

    @NotEmpty
    private List<String> ldapGroups;

    @NotNull
    private Integer tokenPoints;

    //    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", locale = "pl-PL")
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
