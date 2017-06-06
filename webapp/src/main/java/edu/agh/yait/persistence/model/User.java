package edu.agh.yait.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.agh.yait.LdapFascade;
import edu.agh.yait.userData.UserData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
public class User {
    @Autowired
    @JsonIgnore
    @Transient
    private LdapFascade ldapFascade;

    @Id
    @Column
    @JsonIgnore
    private String ldapId;

    private String login;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date firstLogin;

    public String getLdapId() {
        return ldapId;
    }

    public void setLdapId(String ldapId) {
        this.ldapId = ldapId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public void fetchInformation() {
        Optional<UserData> creator = ldapFascade.getUserDataById(getLdapId());
        if (creator.isPresent()) {
            setLogin(creator.get().getLogin());
            setFirstName(creator.get().getName().orElse(null));
            setLastName(creator.get().getSurname().orElse(null));
        }
    }
}
