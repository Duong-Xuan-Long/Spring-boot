package com.example.Securitydemo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController implements ErrorController {
    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user);
        return "profile";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/error-401";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/error-403";
            }
        }
        return "error";
    }

}
