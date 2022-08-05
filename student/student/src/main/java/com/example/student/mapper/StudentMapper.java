package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.model.Student;

public class StudentMapper {
    public static StudentDto toStudentDto(Student student) {
        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());

        return studentDto;
    }
}
