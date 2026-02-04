package com.cozynest.cozynest.controller;


import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozynest.cozynest.model.LoginResponse;
import com.cozynest.cozynest.model.User;
import com.cozynest.cozynest.model.dto.LogEntryDTO;
import com.cozynest.cozynest.model.dto.LoginUserDto;
import com.cozynest.cozynest.model.dto.RegisterUserDto;
import com.cozynest.cozynest.producer.LogProducer;
import com.cozynest.cozynest.service.AuthenticationService;
import com.cozynest.cozynest.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    private final LogProducer authProducer;
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService,LogProducer authProducer) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.authProducer = authProducer;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());


        authProducer.send(new LogEntryDTO("User",authenticatedUser.getId(),"Logged In",authenticatedUser.getUsername(),LocalDateTime.now(),"Some details"));
        return ResponseEntity.ok(loginResponse);
    }
}