package edu.agh.yait;

import edu.agh.yait.mailer.Mailer;
import edu.agh.yait.mailer.RecipientInfo;
import edu.agh.yait.mailer.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@SpringBootApplication
@ComponentScan("edu.agh.yait")
@Configuration
@EnableAutoConfiguration
public class YaitApplication {

	public static void main(String[] args) {
		SpringApplication.run(YaitApplication.class, args);
	/*	String[] mailList = {"marcin.sendera@gmail.com"};
		RecipientInfo recipientInfo = new RecipientInfo(mailList);
		Mailer mailer = new Mailer();
		Ticket ticket; //= new Ticket(24354, new Date(1994,10,10,16,30), new Date(2017, 5, 30, 18, 3), 20, "myverylonghash");
		for(int i = 0; i < 1; i++) {
			mailer.sendMail(recipientInfo, "whatever", "example_template.txt", ticket);
		}*/
	}
}
