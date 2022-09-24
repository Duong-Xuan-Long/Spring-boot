package com.example.MidtermEmployee.entity;

import com.example.MidtermEmployee.repository.CustomRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomRepositoryImpl implements CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Map<String, List<Student>> getMap() {
       return entityManager
                .createQuery("SELECT c.name AS name ,JSON_ARRAYAGG(JSON_OBJECT('id',s.id,'name',s.name)) AS studentList\n" +
                        "FROM student s\n" +
                        "INNER JOIN course_student cs ON s.id =cs.student_id \n" +
                        "INNER  JOIN course c ON cs.course_id=c.id \n" +
                        "GROUP BY c.name ", Tuple.class)
                .getResultList().stream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((String)tuple.get("name")),
                                tuple -> (new ArrayList(Arrays.asList(tuple.get("studentList"))))
                        ));
    }
}
