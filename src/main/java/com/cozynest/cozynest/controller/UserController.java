package com.cozynest.cozynest.controller;


import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozynest.cozynest.model.User;
import com.cozynest.cozynest.repo.UserRepo;


@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepo userRepo;




public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }




@GetMapping("/me")
public Map<String, String> getCurrentUser(Authentication auth) {
    User user = userRepo.findByUsername(auth.getName()).orElseThrow();
    return Map.of(
            "username", user.getUsername(),
            "avatar", user.getAvatar(),
            "bio", user.getBio()
        );
}
}