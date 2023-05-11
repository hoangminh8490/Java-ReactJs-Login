package com.example.demo.api;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/cool-cars")
//    @PreAuthorize("hasAnyRole('app-admin')")
//    @PreAuthorize("hasAuthority('SCOPE_app-role')")
    @RolesAllowed("app-minh")
    public ResponseEntity<?> hello(Principal principal){

        if(!userService.isUserNameAlready(principal.getName())){
            userService.saveUser(principal.getName());
            System.out.println("saved succefully");
        }
        ProfileDTO profile = new ProfileDTO("hello" + principal.getName());
        return ResponseEntity.ok(profile);
    }

}

