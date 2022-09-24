package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.entity.Course;
import com.example.MidtermEmployee.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentRepository extends JpaRepository<CourseStudent,Integer> {
}
