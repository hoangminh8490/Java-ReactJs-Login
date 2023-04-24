package com.example.springapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/auth/checkToken")
    public ResponseEntity<?> checkTokenService(@RequestHeader(name = "Authorization") String authorizationHeader){

    }
}
