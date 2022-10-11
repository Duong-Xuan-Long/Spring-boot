package com.example.Securitydemo.service;

import com.example.Securitydemo.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    public String login(LoginRequest loginRequest, HttpSession session){
        UsernamePasswordAuthenticationToken token=new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        Authentication authentication=authenticationManager.authenticate(token);

        //Luu vao trong context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //set cookie
        session.setAttribute("MY_SESSION",authentication.getName());
        return "login Success";
    }
}
