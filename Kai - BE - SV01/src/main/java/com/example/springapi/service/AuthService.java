package com.example.springapi.service;

import com.example.springapi.dto.TokenDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;
    public String validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<TokenDTO> response = restTemplate.exchange("http://localhost:8080/auth/checkToken", HttpMethod.POST, entity, TokenDTO.class);
        return response.getBody().getToken();
    }
}
