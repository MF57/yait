package edu.agh.yait.controllers;

import edu.agh.yait.LdapHandler;
import edu.agh.yait.dto.LoginDTO;
import edu.agh.yait.userData.UserData;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LdapHandler ldapHandler;

    @RequestMapping(method = RequestMethod.POST)
    public Object login(@Valid @RequestBody LoginDTO loginDTO, Errors result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if(!ldapHandler.auth(loginDTO.getLogin(), loginDTO.getPassword())){
            return ResponseEntity.status(401).body(new CustomErrorObject(""));
        }

        UserData userData = ldapHandler.getUserDataByLogin(loginDTO.getLogin()).get();

        return "{\n" +
                "  \"authenticationToken\": \"sampleAuthToken\"\n" +
                "}";
    }
}
