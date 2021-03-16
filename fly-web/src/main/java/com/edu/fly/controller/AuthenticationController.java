package com.edu.fly.controller;

import com.edu.fly.dto.JWTAuthDTO;
import com.edu.fly.dto.LoginDTO;
import com.edu.fly.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthDTO> login(@RequestBody LoginDTO loginDTO){

        JWTAuthDTO jwtAuthenDTO = authenticationService.authentication(loginDTO);
        return ResponseEntity.ok(jwtAuthenDTO);
    }
}
