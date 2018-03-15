package inciDashboard.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inciDashboard.entities.User;

@RestController
public class UsersController {

    @RequestMapping("/user")
    public User user() {
        return new User("Pepe", "pepe@example.com", "12345");
    }

}