package com.example.BackEndStandBlog.service;

import com.example.BackEndStandBlog.entity.Category;
import com.example.BackEndStandBlog.repository.CategoryRepository;
import com.example.BackEndStandBlog.request.CreateCategoryRequest;
import com.example.BackEndStandBlog.request.UpdateCategoryRequest;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category update(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.getById(id);

        category.setName(updateCategoryRequest.getName());

        return categoryRepository.save(category);
    }

    public Category findById(Integer id) {
        return categoryRepository.getById(id);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory_By_id(id);
    }

    public Category createCategory(CreateCategoryRequest categoryRequest) {
        Random rd=new Random();
        Integer id=rd.nextInt(100);
        Category newCategory=new Category(id,categoryRequest.getName());
        return categoryRepository.save(newCategory);
    }
}
