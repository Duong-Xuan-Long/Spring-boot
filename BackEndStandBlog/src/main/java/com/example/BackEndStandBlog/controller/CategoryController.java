package com.example.BackEndStandBlog.controller;

import com.example.BackEndStandBlog.entity.Category;
import com.example.BackEndStandBlog.repository.CategoryRepository;
import com.example.BackEndStandBlog.request.CreateCategoryRequest;
import com.example.BackEndStandBlog.request.UpdateCategoryRequest;
import com.example.BackEndStandBlog.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("admin/categories")
    public String getCategoryPage(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "admin/category/index";
    }
    @PutMapping("admin/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody UpdateCategoryRequest updateCategoryRequest){
        Category category = categoryService.update(id,updateCategoryRequest);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("admin/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("admin/categories/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }

}
