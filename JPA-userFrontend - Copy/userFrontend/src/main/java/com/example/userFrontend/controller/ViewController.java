package com.example.userFrontend.controller;

import com.example.userFrontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getTodoPage(Model model) {
        model.addAttribute("users",userService.getAll());
        return "index";
    }
    @GetMapping("/detail/{id}")
    public String getUserDetailPage(Model model, @PathVariable int id){
        model.addAttribute("user",userService.getUserId(id));
        return "detail";
    }
    @GetMapping("/create")
    public String createPage(){
        return "create";
    }
}
