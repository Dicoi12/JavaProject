package com.example.ImcBeProj.controller;

import com.example.ImcBeProj.models.User;
import com.example.ImcBeProj.models.dtos.LoginRequest;
import com.example.ImcBeProj.models.dtos.RegisterRequest;
import com.example.ImcBeProj.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final UserService userService;
    public AuthController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody RegisterRequest register){
        return userService.createUser(new User(0,register.getUsername(), register.getPassword()));
    }
}
