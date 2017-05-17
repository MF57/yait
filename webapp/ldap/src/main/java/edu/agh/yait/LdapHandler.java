package edu.agh.yait;

import edu.agh.yait.userData.UserData;
import edu.agh.yait.userData.UserDataAttributesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;
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

    public Optional<UserData> getUserData(String userId) {
        return getUserData(Collections.singletonList(userId)).get(0);
    }

    public List<Optional<UserData>> getUserData(List<String> usersIds) {
        List<UserData> search = ldapTemplate.search("", "(objectclass=person)",   new UserDataAttributesMapper());

        return new ArrayList<>();
    }

    public List<UserData> getUserDataByGroupName(String groupName) {
        return new ArrayList<>();
    }


    public List<String> getGroups() {
        return new ArrayList<>();

    }



}
