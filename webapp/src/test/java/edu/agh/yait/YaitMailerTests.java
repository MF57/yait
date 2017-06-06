package edu.agh.yait;

import edu.agh.yait.mailer.TicketManager;
import edu.agh.yait.persistence.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaitMailerTests {

    @Autowired
    TicketManager ticketManager;

    @Test
    public void testCreatingToken() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        String value = ticketManager.generateToken(ticket);
        assertNotNull(value);
    }

    @Test
    public void testValidatingToken() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        String value = ticketManager.generateToken(ticket);
        assertNotNull(value);
        Integer validatedId = ticketManager.validateToken(value);
        assertEquals(new Integer(1), validatedId);
    }



}