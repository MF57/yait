package edu.agh.yait.userData;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import edu.agh.yait.LdapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by mf57 on 22.05.2017.
 */
@Component
public class UserDataCache {

    private final LdapHandler ldapHandler;

    private final LoadingCache<String, UserData> cache;

    @Autowired
    public UserDataCache(LdapHandler ldapHandler) {
        this.ldapHandler = ldapHandler;
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, UserData>() {
                    @Override
                    public UserData load(String key) throws Exception {

                        return ldapHandler.getUserDataById(key)
                                .orElseThrow(() -> new IllegalStateException("There is no such user in ldap database"));
                    }
                });

    }

    /**
     * This method retrieves userdata from cache and if it doesnt exist, it queries ldap
     * Note: If you have multiple ids, please use ldapHandler.getUserDataByIds instead of calling this method in the loop
     * because it can potentially make multiple requests to the ldap at once.
     * @param id - id of user data to be retrieved from cache
     * @return - user data of given id
     * @throws ExecutionException - when something went wrong during operation on cache
     */
    public UserData getUserDataById(String id) throws ExecutionException {
        return cache.get(id);
    }

    public Optional<UserData> getUserDataByLogin(String login) {
        return cache.asMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(userData -> userData.getLogin().equals(login)).findAny();
    }

    /**
     * Puts all of the passed user data to the cache
     * @param userData - user data to placed in cache
     */
    public void putAll(List<UserData> userData) {
        cache.putAll(userData.stream().collect(Collectors.toMap(UserData::getId, Function.identity())));
    }

    /**
     * Refresh all of the cache
     */
    public void refresh() {
        Set<String> userIds = cache.asMap().keySet();
        cache.invalidateAll();
        if (userIds.isEmpty()) {
            return;
        }
        Map<String, UserData> upToDateUsers = ldapHandler.getUserDataByIds(new ArrayList<>(userIds)).stream()
                .collect(Collectors.toMap(UserData::getId, Function.identity()));
        cache.putAll(upToDateUsers);

    }

}
