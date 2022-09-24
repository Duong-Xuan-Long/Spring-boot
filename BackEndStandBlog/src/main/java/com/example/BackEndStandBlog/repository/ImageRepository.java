package com.example.BackEndStandBlog.repository;

import com.example.BackEndStandBlog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> getByUser_id(Integer id);
}