package edu.agh.yait.userData;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import edu.agh.yait.LdapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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

    public UserData getUserData(String id) throws ExecutionException {
        return cache.get(id);
    }
}
