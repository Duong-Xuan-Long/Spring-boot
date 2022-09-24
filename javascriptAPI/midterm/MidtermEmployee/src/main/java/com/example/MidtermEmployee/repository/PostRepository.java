package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {

}
