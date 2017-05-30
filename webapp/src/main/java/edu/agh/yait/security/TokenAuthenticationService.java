package edu.agh.yait.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days

    //TODO: move secret to properties
    static final String SECRET = "ThisIsASecret";
    static final String AUTHORIZATION_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonToken = mapper.createObjectNode();
        jsonToken.put("authenticationToken", JWT);
        res.getWriter().append(jsonToken.toString());
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}