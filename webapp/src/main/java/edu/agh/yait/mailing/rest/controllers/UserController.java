package edu.agh.yait.mailing.rest.controllers;

import edu.agh.yait.mailing.core.entity.User;
import edu.agh.yait.mailing.core.repositories.UserRepository;
import edu.agh.yait.mailing.core.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcinsendera on 09.05.2017.
 */



import java.util.List;

/**
 * Created by andresmerida on 3/3/2016.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
