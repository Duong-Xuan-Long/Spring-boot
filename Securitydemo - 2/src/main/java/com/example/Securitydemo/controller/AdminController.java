package com.example.Securitydemo.controller;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminController {
    @GetMapping("/admin/blogs")
    public String getBlog(Principal principal, Model model){
        model.addAttribute("user",principal.getName() );
        return "blog";
    }

    @GetMapping("/admin/users")
    public String getUser(Authentication authentication, Model model){
        UserDetails user= (UserDetails) authentication.getPrincipal();
        model.addAttribute("user",user);
        return "user";
    }
}
