package edu.agh.yait;

import edu.agh.yait.mailer.Ticket;
import edu.agh.yait.mailer.TicketManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaitMailerTests {

    @Test
    public void testGrantingPoints() {

        TicketManager ticketManager = new TicketManager();
        List<String> addresses = new ArrayList<>();
        addresses.add("john@mail.com");
        addresses.add("mike@example.com");
        Date date = new Date();
        Set<Ticket> tickets = ticketManager.grantPoints(addresses, 10, date);
        assertEquals(2, tickets.size());

    }

    @Test
    public void testCreatingToken() {
        TicketManager ticketManager = new TicketManager();
        String value = ticketManager.generateToken(new Ticket());
        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJ0aWNrZXQiOiIxMjM0NTY3ODkwIn0.vC1pOy4ppImfZm0AzmsQaanmOwLlgeCFY2H3nOZhTeU", value);
    }



}