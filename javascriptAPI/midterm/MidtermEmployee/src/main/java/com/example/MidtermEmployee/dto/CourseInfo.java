package com.example.MidtermEmployee.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseInfo implements Serializable{
    private String name;
    private StudentInfo[] studentList;
    public CourseInfo(String name, String studentList) {
        this.name = name;
        if (studentList != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.studentList = mapper.readValue(studentList,StudentInfo[].class);
            } catch (IOException e) {
                this.studentList = null;
            }
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class StudentInfo implements Serializable {
        private Long id;
        private String name;
    }
}
