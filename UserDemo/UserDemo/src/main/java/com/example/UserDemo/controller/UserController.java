package com.example.UserDemo.controller;

import com.example.UserDemo.request.SignInRequest;
import com.example.UserDemo.userDto.UserDto;
import com.example.UserDemo.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public UserDto signIn(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }
}
