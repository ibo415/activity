package com.example.activity.controller;

import com.example.activity.entity.Users;
import com.example.activity.repository.UsersRepository;
import com.example.activity.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")

public class UserController {


    private final UserService userService;
    private final UsersRepository usersRepository;

    public UserController(UserService userService, UsersRepository usersRepository) {
        this.userService = userService;
        this.usersRepository = usersRepository;
    }


    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user){

        return userService.verify(user);

    }
}