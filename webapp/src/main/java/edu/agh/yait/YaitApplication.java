package edu.agh.yait;

import edu.agh.yait.mailer.Mailer;
import edu.agh.yait.mailer.RecipientInfo;
import edu.agh.yait.persistence.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("edu.agh.yait")
@Configuration
@EnableScheduling
@EnableAutoConfiguration
@Import(LdapConfig.class)
public class YaitApplication {

//    private static Mailer mailer;
//
//    @Autowired
//    public void setMailer(Mailer mailer) {
//        YaitApplication.mailer = mailer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(YaitApplication.class, args);
//        Ticket ticket = new Ticket();
//        ticket.setId(1);
//        mailer.sendMail("roxelum@gmail.com", ticket);
    }
}
