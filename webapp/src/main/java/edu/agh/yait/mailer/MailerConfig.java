package edu.agh.yait.mailer;

/**
 * Created by marcinsendera on 23.05.2017.
 */
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;


@SuppressWarnings("deprecation")
@Configuration
@ComponentScan(basePackages = "edu.agh.yait.mailer")
@PropertySource("classpath:application.properties")
public class MailerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private @Value("${mailer.host}") String host;
    private @Value("${mailer.port}") int port;
    private @Value("${mailer.username}") String username;
    private @Value("${mailer.password}") String password;
    private @Value("${mailer.mail.smtp.starttls.enable}") String mailSmtpStarttlsEnable;
    private @Value("${mailer.mail.smtp.auth}") String mailSmtpAuth;
    private @Value("${mailer.mail.transport.protocol}") String mailTransportProtocol;
    private @Value("${mailer.mail.debug}") String mailDebug;

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
