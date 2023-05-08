package com.example.springapi.api;

import com.example.springapi.dto.TokenDTO;
import com.example.springapi.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class    AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth/checkToken")
    public ResponseEntity<?> checkTokenService(@RequestHeader(name = "Authorization") String authorizationHeader){
        return ResponseEntity.ok().build();
    }
}
