package edu.agh.yait;

import edu.agh.yait.userData.UserData;
import edu.agh.yait.userData.UserDataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created by mf57 on 30.05.2017.
 */
@Component
public class LdapFascade {

    private static final Logger logger = LoggerFactory.getLogger(LdapFascade.class);


    private final UserDataCache cache;
    private final LdapHandler handler;


    @Autowired
    public LdapFascade(UserDataCache cache, LdapHandler handler) {
        this.cache = cache;
        this.handler = handler;
    }

    public boolean auth(String login, String password) {
        return handler.auth(login, password);
    }

    public Optional<UserData> getUserDataById(String userId) {
        try {
            return Optional.ofNullable(cache.getUserDataById(userId));
        } catch (ExecutionException e) {
            logger.error("Error in retrieving from user data cache", e);
            return Optional.empty();
        }
    }

    public List<UserData> getUserDataByIds(List<String> usersIds) {
        List<UserData> userDatas = handler.getUserDataByIds(usersIds);
        cache.putAll(userDatas);
        return userDatas;
    }

    public Optional<UserData> getUserDataByLogin(String login) {
        Optional<UserData> cachedUserData = cache.getUserDataByLogin(login);
        if (cachedUserData.isPresent()) {
            return cachedUserData;
        } else {
            return handler.getUserDataByLogin(login);
        }
    }


    public List<UserData> getUserDataByLogins(List<String> logins) {
        List<UserData> userDatas = handler.getUserDataByLogins(logins);
        cache.putAll(userDatas);
        return userDatas;
    }


    public List<UserData> getUserDataByGroupName(String groupName) {
        List<UserData> groupsUserData = handler.getUserDataByGroupName(groupName);
        cache.putAll(groupsUserData);
        return groupsUserData;
    }

    public List<String> getGroups() {
        return handler.getGroups();
    }


}
