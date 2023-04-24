package com.example.springapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class AuthService {

    private RestTemplate restTemplate;

    public boolean validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8080/auth/checkToken", HttpMethod.POST, entity, Boolean.class);
        return response.getBody();
    }
}
