package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@RestController
public class UserController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/hello-oauth")
    public ResponseEntity<?> listUser(){

        UserDTO userDTO = new UserDTO("Welcome to Okta");
        return ResponseEntity.ok(userDTO);
    }

}

