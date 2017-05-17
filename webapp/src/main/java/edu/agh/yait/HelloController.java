package edu.agh.yait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by mf57 on 05.04.2017.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    private final LdapHandler ldapHandler;

    @Autowired
    public HelloController(LdapHandler ldapHandler) {
        this.ldapHandler = ldapHandler;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/ldapAuth", method = RequestMethod.GET)
    public String ldapAuth(@RequestParam String login, @RequestParam String password) {
        if (ldapHandler.auth(login, password)) {
            return "OK";
        } else {
            return "Not Authorized";
        }
    }

    @RequestMapping(value = "/ldap", method = RequestMethod.GET)
    public String ldap() {
        ldapHandler.getUserData(new ArrayList<>());
        return "ok";
    }

}
