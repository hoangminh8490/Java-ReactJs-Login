package com.example.demo.api;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/cool-cars")
    public String hello(Principal principal){
        if(!userService.isUserNameAlready(principal.getName())){
            userService.saveUser(principal.getName());
            System.out.println("saved succefully");
        }
        return "hello" + principal.getName();
    }

}

