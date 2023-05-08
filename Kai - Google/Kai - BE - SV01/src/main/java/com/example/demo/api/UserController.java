package com.example.demo.api;

import com.example.demo.config.CustomInterceptor;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CustomInterceptor customInterceptor;

    @GetMapping("/list")
    public ResponseEntity<?> listUser(){
        UserDTO userDTO = new UserDTO("Welcome to KAL 01");
        return ResponseEntity.ok(userDTO);
    }
}
