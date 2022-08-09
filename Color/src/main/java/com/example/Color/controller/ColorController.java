package com.example.Color.controller;

import com.example.Color.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {

    @Autowired
    private ColorService colorService;
    @GetMapping("/random-color")
    public String randomColor(@RequestParam int type){
        return colorService.randomColor(type);
    }
}
