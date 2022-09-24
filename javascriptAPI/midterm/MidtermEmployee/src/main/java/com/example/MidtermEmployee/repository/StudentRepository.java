package com.example.MidtermEmployee.repository;
import com.example.MidtermEmployee.dto.CourseInfo;
import com.example.MidtermEmployee.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(nativeQuery =true,value="SELECT *\n" +
            "FROM  student s \n" +
            "INNER JOIN course_student cs ON s.id =cs.student_id \n" +
            "INNER  JOIN course c ON cs.course_id=c.id \n" +
            "WHERE c.name='math'\n" +
            "AND c.name!='music'")
    List<Student> getMathList();


}
