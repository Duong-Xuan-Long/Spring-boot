package com.example.BackEndStandBlog.controller;

import com.example.BackEndStandBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    BlogService blogService;

    @GetMapping("/web/home")
    public String getHomePage() {
        return "/web/htmlWeb/home";
    }

    @GetMapping("web/blog-entries")
    public String getBlogEntriesPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogInfo());
        return "/web/htmlWeb/blog-entries";
    }

    @GetMapping("web/post-details/{id}")
    public String getPostDetailsPage(Model model, @PathVariable Integer id) {
        model.addAttribute("blog", blogService.getAllBlogInfoById(id));
        return "/web/htmlWeb/post-details";
    }
}
