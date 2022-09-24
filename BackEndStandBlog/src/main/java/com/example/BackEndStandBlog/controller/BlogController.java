package com.example.BackEndStandBlog.controller;

import com.example.BackEndStandBlog.entity.Blog;
import com.example.BackEndStandBlog.request.CreateBlogRequest;
import com.example.BackEndStandBlog.service.BlogService;
import com.example.BackEndStandBlog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model){
        model.addAttribute("blogs",blogService.getAllBlogDto());
        return "admin/blog/blog-index";
    }

    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model){
        Integer userId=1;
        model.addAttribute("blogs",blogService.getALlBlogDtoByUserId(userId));
        return "admin/blog/blog-yourself";

    }

    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "admin/blog/blog-create";
    }

    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(){
        return "admin/blog/blog-detail";
    }

    //tao bai viet
    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest createBlogRequest){
        Integer userId=1;
        return ResponseEntity.ok(blogService.createBlog(userId,createBlogRequest));
    }
}
