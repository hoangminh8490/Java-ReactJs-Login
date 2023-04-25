package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @GetMapping("/list")
    public ResponseEntity<?> listUser(){
        UserDTO userDTO = new UserDTO("Welcome to KAL 03");
        return ResponseEntity.ok(userDTO);
    }
}
