package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController {

    @GetMapping("/hello-oauth")
    public ResponseEntity<?> listUser(){
        UserDTO userDTO = new UserDTO("Welcome to Okta");
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/cool-cars")
    public String hello(Principal principal){
        return "hello" + principal;
    }

}

