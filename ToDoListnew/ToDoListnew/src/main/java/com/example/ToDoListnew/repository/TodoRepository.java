package com.example.ToDoListnew.repository;

import com.example.ToDoListnew.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findByStatus(Boolean status);
}
