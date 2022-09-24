package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
