package com.example.shopThymeleaf.controller;

import com.example.shopThymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/products/top-sell")
    public ResponseEntity<?> getTopSellProducts(@RequestParam int count){
        return ResponseEntity.ok(productService.getProductsHot(count));
    }
    @GetMapping("products/top-discount")
    public ResponseEntity<?> getTopDiscountProducts(@RequestParam int count){
        return ResponseEntity.ok(productService.getProductsDiscount(count));
    }
}
