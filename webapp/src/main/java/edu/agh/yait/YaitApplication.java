package edu.agh.yait;

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

    public static void main(String[] args) {
        SpringApplication.run(YaitApplication.class, args);
    }
}
