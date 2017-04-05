package edu.agh.yait;

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
