package edu.agh.yait;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Created by mf57 on 12.04.2017.
 */
@Configuration
@ComponentScan("edu.agh.yait")
public class LdapConfig {


    @Value("${ldap.url}")
    private String ldapUrl;


    @Value("${ldap.base}")
    private String ldapBase;


    @Value("${ldap.user}")
    private String ldapUser;


    @Value("${ldap.password}")
    private String ldapPassword;




    @Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setBase(ldapBase);
        contextSource.setUserDn(ldapUser);
        contextSource.setPassword(ldapPassword);
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }


}
