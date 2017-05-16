package edu.agh.yait.mailer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

/**
 * Created by marcinsendera on 09.05.2017.
 */


@Configuration
public class MailingConfig {

    @Value("${mail.protocol}") // this is to read variable from application.properties
    private String mailProtocol;

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Value("${mail.support.username}")
    private String userName;

    @Value("${mail.support.password}")
    private String password;

@Bean
    public JavaMailSender javaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setProtocol(mailProtocol);
    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(userName);
    javaMailSender.setPassword(password);
    javaMailSender.setJavaMailProperties(getMailProperties());
    return javaMailSender;
    }

    /*
     * FreeMarker configuration.
     */

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/fmtemplates/");
        return bean;
    }


    private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.debug", "false");
    return properties;
    }
}
