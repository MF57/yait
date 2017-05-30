package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@SuppressWarnings("deprecation")
@Configuration
@PropertySource("classpath:application.properties")
public class MailerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${mailer.host}")
    private String host;

    @Value("${mailer.port}")
    private int port;

    @Value("${mailer.username}")
    private String username;

    @Value("${mailer.password}")
    private String password;

    @Value("${mailer.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${mailer.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${mailer.mail.transport.protocol}")
    private String mailTransportProtocol;

    @Value("${mailer.mail.debug}")
    private String mailDebug;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        javaMailProperties.put("mail.smtp.auth", mailSmtpAuth);
        javaMailProperties.put("mail.transport.protocol", mailTransportProtocol);
        javaMailProperties.put("mail.debug", mailDebug);

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
