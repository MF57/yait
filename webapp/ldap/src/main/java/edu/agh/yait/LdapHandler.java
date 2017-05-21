package edu.agh.yait;

import edu.agh.yait.userData.UserData;
import edu.agh.yait.userData.UserDataAttributesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.filter.OrFilter;
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


    /**
     *
     * @param login - login of the user which should be tried to auth
     * @param password - password of the user which should be tried to auth
     * @return true if ldap auth succeded, false otherwise (+ reason of failure logging)
     */
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

    /**
     *
     * @param userId - user unique ldap identifier (not login)
     * @return empty optional if user of given id  doesnt exist, UserData of given userId otherwise
     */
    public Optional<UserData> getUserDataById(String userId) {
        List<UserData> result = getUserDataByIds(Collections.singletonList(userId));
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }


    /**
     *
     * @param usersIds
     * @return
     */
    public List<UserData> getUserDataByIds(List<String> usersIds) {
        AndFilter baseFilter = new AndFilter();
        OrFilter alternateFilter = new OrFilter();
        usersIds.forEach(id -> alternateFilter.or(new EqualsFilter("ipaUniqueID", id)));
        baseFilter.and(new EqualsFilter("objectclass", "posixAccount"));
        baseFilter.and(alternateFilter);

        return ldapTemplate.search("", baseFilter.encode(), new UserDataAttributesMapper());
    }


    /**
     *
     * @param login - login of the user
     * @return empty optional if user of given login  doesnt exist, UserData of given login otherwise
     */
    public Optional<UserData> getUserDataByLogin(String login) {
        List<UserData> result = getUserDataByLogins(Collections.singletonList(login));
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }

    /**
     *
     * @param logins
     * @return
     */
    public List<UserData> getUserDataByLogins(List<String> logins) {
        AndFilter baseFilter = new AndFilter();
        OrFilter alternateFilter = new OrFilter();
        logins.forEach(login -> alternateFilter.or(new EqualsFilter("uid", login)));
        baseFilter.and(new EqualsFilter("objectclass", "posixAccount"));
        baseFilter.and(alternateFilter);

        return ldapTemplate.search("", baseFilter.encode(), new UserDataAttributesMapper());
    }


    public List<UserData> getUserDataByGroupName(String groupName) {
        AndFilter baseFilter = new AndFilter();
        baseFilter.and(new EqualsFilter("objectclass", "posixAccount"));
        baseFilter.and(new LikeFilter("memberof", "*" + groupName + "*"));

        return ldapTemplate.search("", baseFilter.encode(), new UserDataAttributesMapper());
    }


    public List<String> getGroups() {
        return new ArrayList<>();

    }



}
