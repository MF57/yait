package edu.agh.yait.mailer;

import edu.agh.yait.persistence.model.Ticket;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class TicketManager {
    private static final Logger logger = LoggerFactory.getLogger(TicketManager.class);

    @Value("${mailer.ticket.secretKey}")
    private String secretKey;

    @Value("${mailer.ticket.url}")
    private String url;

    /**
     * Creates token for Ticket.
     * Ticket id is encoded inside JWT token.
     * Ticket is not changed during this process.
     *
     */
    public String generateToken(Ticket ticket) {
        Integer ticketId = ticket.getId();
        String token = null;
        try {
            token = Jwts.builder()
                    .claim("ticket", ticketId)
                    .signWith(SignatureAlgorithm.HS256, this.secretKeyBytes())
                    .compact();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return token;
    }

    /**
     * Returns ticket id encoded in JWT string.
     * The ticket may not exist, so backend controllers must test and get it from database.
     *
     */
    public Integer validateToken(String token) {
        Integer ticketId = null;
        try {
            ticketId = Jwts.parser().setSigningKey(this.secretKeyBytes())
                    .parseClaimsJws(token).getBody().get("ticket", Integer.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ticketId;
    }

    private byte[] secretKeyBytes() throws UnsupportedEncodingException {
        return this.secretKey.getBytes("UTF-8");
    }


    public String getTokenUrl(Ticket ticket) {
        String token = generateToken(ticket);
        return url + token + "/";
    }

}