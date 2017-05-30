package edu.agh.yait.security;

import edu.agh.yait.LdapHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Maciek on 2017-05-30.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapHandler ldapHandler;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("CustomAuthenticationProvider: auhtenticate");
        if (true /* auth method */ /*ldapHandler.auth(login, password)*/) {

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    login, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}