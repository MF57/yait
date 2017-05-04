package edu.agh.yait;

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

    @Autowired
    private LdapTemplate ldapTemplate;


    //Will throw exception if auth failed, won't retriece any data about user
    public void auth(String login, String password) {
        ldapTemplate.authenticate(
                query().where("objectClass").is("posixAccount")
                        .and(query().where("uid").is(login)),
                password);
    }
    public Map<UserId, Optional<User>> getUsers(List<UserId> ids) {
        return new HashMap<>();
    }

    public List<User> getUserByGroup(GroupId groups) {
        return new ArrayList<>();
    }

    public List<String> getGroups() {

        return new ArrayList<>();

    }


    class User {

    }
    class UserId {

    }
    class GroupId {

    }

}
