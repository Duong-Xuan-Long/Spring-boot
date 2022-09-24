package com.example.MidtermEmployee.entity;

import com.example.MidtermEmployee.dto.CourseInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "courseInfo",
                classes = @ConstructorResult(
                        targetClass = CourseInfo.class,
                        columns = {
                                @ColumnResult(name = "name",type=String.class),
                                @ColumnResult(name = "studentList", type=String.class),
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getAllCourseInfo",
        resultSetMapping = "courseInfo",
        query = "SELECT c.name AS name ,JSON_ARRAYAGG(JSON_OBJECT('id',s.id,'name',s.name)) AS studentList\n" +
                "FROM student s\n" +
                "INNER JOIN course_student cs ON s.id =cs.student_id \n" +
                "INNER  JOIN course c ON cs.course_id=c.id \n" +
                "GROUP BY c.name ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "student")
    private List<CourseStudent> courseStudent;
}