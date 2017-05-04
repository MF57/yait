package edu.agh.yait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mf57 on 05.04.2017.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    LdapHandler ldapHandler;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/ldap", method = RequestMethod.GET)
    public String ldap() {
        ldapHandler.auth("studen1", "Haslo1234!");
        return "SUCCESS AUTH";
    }

}
