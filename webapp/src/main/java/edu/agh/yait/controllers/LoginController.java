package edu.agh.yait.controllers;

import edu.agh.yait.dto.LoginDTO;
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
    @RequestMapping(method = RequestMethod.POST)
    public Object login(@Valid @RequestBody LoginDTO loginDTO, Errors result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return "{\n" +
                "  \"authenticationToken\": \"sampleAuthToken\"\n" +
                "}";
    }
}
