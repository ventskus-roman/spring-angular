package by.roman.ventskus.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
@RestController
public class UserResource {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
