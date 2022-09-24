package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
