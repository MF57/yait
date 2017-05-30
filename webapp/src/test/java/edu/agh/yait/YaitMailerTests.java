package edu.agh.yait;

import edu.agh.yait.mailer.TicketManager;
import edu.agh.yait.persistence.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaitMailerTests {
    @Autowired
    TicketManager ticketManager;

    @Test
    public void testGrantingPoints() {

    }

    @Test
    public void testCreatingToken() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        String value = ticketManager.generateToken(ticket);
        assertNotNull(value);
//        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJ0aWNrZXQiOiIxMjM0NTY3ODkwIn0.vC1pOy4ppImfZm0AzmsQaanmOwLlgeCFY2H3nOZhTeU", value);
    }



}