package edu.agh.yait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by mf57 on 12.04.2017.
 */
@Component
public class LdapHandler {

    private static final Logger logger = LoggerFactory.getLogger(LdapHandler.class);

    private final LdapTemplate ldapTemplate;


    @Autowired
    public LdapHandler(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }


    public boolean auth(String login, String password) {
        try {
            ldapTemplate.authenticate(query()
                            .where("objectClass").is("posixAccount")
                            .and(query().where("uid").is(login)),
                    password);
            return true;
        } catch (Exception e) {
            logger.error("Could not auth user with login: " + login + " and password: " + password + " - reason: ", e);
            return false;
        }
    }

//    public Map<UserId, Optional<User>> getUsers(List<UserId> ids) {
//        return new HashMap<>();
//    }
//
//    public List<User> getUserByGroup(GroupId groups) {
//        return new ArrayList<>();
//    }
//
//    public List<String> getGroups() {
//        return new ArrayList<>();
//
//    }



}
