package com.example.SignInSecurity.controller;

import com.example.SignInSecurity.request.LoginRequest;
import com.example.SignInSecurity.request.RegisterRequest;
import com.example.SignInSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session){
        return authService.login(loginRequest,session);
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        return authService.logout(session);
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token){
        return authService.confirm(token);
    }
}
