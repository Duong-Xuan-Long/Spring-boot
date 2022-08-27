package com.example.thymeleaf23.fragment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }
    @GetMapping("/about")
    public String getAboutPage(){
        return "about";
    }
}
