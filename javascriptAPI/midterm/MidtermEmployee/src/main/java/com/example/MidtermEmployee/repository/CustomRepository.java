package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomRepository {
    Map<String, List<Student>> getMap();
}
