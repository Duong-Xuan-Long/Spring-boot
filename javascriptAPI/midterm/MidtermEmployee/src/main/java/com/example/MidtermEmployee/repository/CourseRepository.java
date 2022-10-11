package com.example.MidtermEmployee.repository;

import com.example.MidtermEmployee.dto.CourseInfo;
import com.example.MidtermEmployee.entity.Course;
import com.example.MidtermEmployee.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<Course,Integer> {
        @Query(nativeQuery = true,value="SELECT AVG(cs.score) FROM course c INNER  JOIN course_student cs ON cs.course_id=c.id WHERE c.name=?1")
        Double getAVG(String name);
        @Query(nativeQuery =true,name="getAllCourseInfo")
        List<CourseInfo> getAllCourseInfo();

        @Query(nativeQuery=true,value="SELECT c.name ,JSON_ARRAYAGG(JSON_OBJECT('id',s.id,'name',s.name)) AS studentList FROM student s\n" +
                "INNER JOIN course_student cs ON s.id =cs.student_id \n" +
                "INNER  JOIN course c ON cs.course_id=c.id \n" +
                "GROUP BY c.name " )
        Map<String,List<Student>> TestGetList();
}
