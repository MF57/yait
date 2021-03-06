package edu.agh.yait;

import edu.agh.yait.userData.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mf57 on 05.04.2017.
 */

//TODO Endpoint for debugging, to be removed
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

    @RequestMapping(value = "/ldapid", method = RequestMethod.GET)
    public List<UserData> ldapId() {
        return ldapHandler.getUserDataByIds(Arrays.asList("5f316776-fc6b-11e6-a549-001a4a16016d",
                "sadasdasd", "690528c8-fc6b-11e6-abc5-001a4a16016d", "6e8b3de6-fc6b-11e6-abc5-001a4a16016d"));
    }

    @RequestMapping(value = "/ldapLogin", method = RequestMethod.GET)
    public List<UserData> ldapLogin() {
        return ldapHandler.getUserDataByLogins(Arrays.asList("kaczmare",
                "sadasdasd", "zawadzka"));
    }

    @RequestMapping(value = "/ldapGroup", method = RequestMethod.GET)
    public List<UserData> ldapGroups() {
        return ldapHandler.getUserDataByGroupName("students");
    }

    @RequestMapping(value = "/ldapGroupNames", method = RequestMethod.GET)
    public List<String> ldapGroupsNames() {
        return ldapHandler.getGroups();
    }

}
