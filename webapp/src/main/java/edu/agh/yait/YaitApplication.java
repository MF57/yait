package edu.agh.yait;

import edu.agh.yait.mailer.Mailer;
import edu.agh.yait.mailer.RecipientInfo;
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

	public static void main(String[] args) {
		SpringApplication.run(YaitApplication.class, args);
	}
}
