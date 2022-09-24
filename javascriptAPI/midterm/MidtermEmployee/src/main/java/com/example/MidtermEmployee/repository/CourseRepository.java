package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.dto.CourseInfo;
import com.example.MidtermEmployee.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
        @Query(nativeQuery = true,value="SELECT AVG(cs.score) FROM course c INNER  JOIN course_student cs ON cs.course_id=c.id WHERE c.name=?1")
        Double getAVG(String name);
        @Query(nativeQuery =true,name="getAllCourseInfo")
        List<CourseInfo> getAllCourseInfo();

}
