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

@SpringBootApplication
@ComponentScan("edu.agh.yait")
@Configuration
@EnableAutoConfiguration
public class YaitApplication {

//    private static Mailer mailer;
//
//    @Autowired
//    public void setMailer(Mailer mailer) {
//        YaitApplication.mailer = mailer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(YaitApplication.class, args);
//        String[] mailList = {"marcin.sendera@gmail.com"};
//        RecipientInfo recipientInfo = new RecipientInfo(mailList);
//        Ticket ticket = new Ticket();//new Ticket(24354, new Date(1994,10,10,16,30), new Date(2017, 5, 30, 18, 3), 20, "myverylonghash");
//        ticket.setId(1);
//
//        mailer.sendMail(recipientInfo, "whatever", "example_template.txt", ticket);
    }
}
