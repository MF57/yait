package edu.agh.yait.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.agh.yait.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Configuration
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${admins}")
    private String admins;


    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        System.out.println("JWTLoginFilter: attemptAuthentication()");
        LoginDTO creds = new ObjectMapper().readValue(req.getInputStream(), LoginDTO.class);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        if(Arrays.asList(admins.split(".")).toString().contains(creds.getLogin())) grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    creds.getLogin(),
                    creds.getPassword(),
                    grantedAuths
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }
}