package edu.agh.yait.mailer;

import edu.agh.yait.persistence.model.Ticket;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class TicketManager {
    private static final Logger logger = LoggerFactory.getLogger(TicketManager.class);

    private String key = "secret";
    private String url = "http://www.vote.iiet.pl/token/";
    /**
     * Validate string token
     *
     * @param token
     * @return
     */
    public TokenStatus validateToken(String token) {
        // decode token to ticket
        // if cannot decode return null, INCORRECT
        // if ticket found but expired return null, EXPIRED
        // otherwise return ticket, VALID (doesn't matter if points are left or not)
        return new TokenStatus(null, TokenStatusType.INCORRECT);
    }

    public String generateToken(Ticket ticket) {
        Claims claims = Jwts.claims().setId(ticket.getId().toString());
        claims.put("create", ticket.getCreationDate());
        claims.put("expire", ticket.getExpirationDate());
        claims.put("points", ticket.getPoints());
        String token = null;
        try {
            token = Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, key.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return token;
    }

    public String getTokenUrl(Ticket ticket) {
        String token = generateToken(ticket);
        return url + token + "/";
    }

}