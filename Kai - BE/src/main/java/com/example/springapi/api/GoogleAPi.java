package com.example.springapi.api;

import com.example.springapi.dto.JwtLoginGoogle;
import com.example.springapi.dto.JwtResponse;
import com.example.springapi.dto.TokenDTO;
import com.example.springapi.entity.UserEntity;
import com.example.springapi.security.jwt.JwtTokenUtil;
import com.example.springapi.service.JwtUserDetailsService;
import com.example.springapi.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/oauth")
public class GoogleAPi {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${google.clientId}")
    String googleClientId;

    @Value("${secret.code}")
    String secretPsw;

    @PostMapping("/google")
    public  ResponseEntity<?> loginGoogle(@RequestBody TokenDTO tokenDTO) throws IOException {
       try{
           final NetHttpTransport  netHttpTransport = new NetHttpTransport();
           final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
           GoogleIdTokenVerifier.Builder verifier =
                   new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                           .setAudience(Collections.singletonList(googleClientId));
           final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDTO.getToken());
            if(googleIdToken != null){
                final GoogleIdToken.Payload payload = googleIdToken.getPayload();
                String email = payload.getEmail();
                JwtLoginGoogle jwt = new JwtLoginGoogle();
                UserEntity userEntity = new UserEntity();
                if(userService.isEmailAlready(email)){
                    userEntity = userService.getUserByMail(email);
                }else{
                    userEntity = userService.saveEmail(email);
                }
                jwt.setEmail(userEntity.getEmail());
                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername(email);
                authenticate(jwt.getEmail());
                String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(token));
            }

       }
       catch ( IOException e){

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
        return ResponseEntity.badRequest().build();
    }

    private void authenticate(String email) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, this.secretPsw));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
