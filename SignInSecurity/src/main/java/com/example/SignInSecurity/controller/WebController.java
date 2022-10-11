package com.example.SignInSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public String getHome(){
        return "Home page";
    }
    @GetMapping("/profile")
    public String getProfilePage(){
        return "Profile page";
    }
}
