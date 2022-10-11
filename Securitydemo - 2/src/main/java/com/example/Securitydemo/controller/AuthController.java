package com.example.Securitydemo.controller;

import com.example.Securitydemo.request.LoginRequest;
import com.example.Securitydemo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session){
        return authService.login(loginRequest,session);
    }
}
