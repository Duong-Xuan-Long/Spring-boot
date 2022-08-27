package com.example.shopThymeleaf.controller;

import com.example.shopThymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WebController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("hotProducts",productService.getProductsHot(4));
        model.addAttribute("discountProducts",productService.getProductsDiscount(4));
        return "index";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model){
        model.addAttribute("products",productService.getProducts());
        return "shop";
    }

    @GetMapping("/contact")
    public String getContactPage(){
        return "contact";
    }

    @GetMapping("/buy")
    public String getBuyPage(){
        return "buy";
    }
}
