package com.example.ImcBeProj.controller;

import com.example.ImcBeProj.models.User;
import com.example.ImcBeProj.models.dtos.LoginRequest;
import com.example.ImcBeProj.repositories.UserRepository;
import com.example.ImcBeProj.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public Iterable<User> findall(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/getByUsername")
    public Optional<User> getByUsername(@RequestParam(required = true) String username){
        return userService.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}
