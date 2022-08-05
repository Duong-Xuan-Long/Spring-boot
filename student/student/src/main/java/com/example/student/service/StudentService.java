package com.example.student.service;

import com.example.student.dto.StudentDto;
import com.example.student.exception.BadRequestException;
import com.example.student.exception.NotFoundException;
import com.example.student.mapper.StudentMapper;
import com.example.student.model.Student;
import com.example.student.request.CreateStudentRequest;
import com.example.student.request.UpdateStudentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StudentService {
    //Tao danh sach students
    private List<Student> students;
    @Autowired
    private ModelMapper modelMapper;
    public StudentService(){
        students=new ArrayList<>();
        students.add(new Student(1,"Nguyen Van A","1@gmail.com","111"));
        students.add(new Student(2,"Tran Van B","2@gmail.com","222"));
        students.add(new Student(3,"Ngo Thi C","3@gmail.com","333"));
        students.add(new Student(4,"Bui Van D","4@gmail.com","444"));
    }
    //Lay ra danh sach tat ca cac sinh vien
    public List<StudentDto> getAll(){
        return students.stream().map(student-> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
    }
    public StudentDto getStudentByid(int id) {
//        Optional<Student> studentOptional=findByid(id);
//        if(studentOptional.isPresent()){
//            Student student=studentOptional.get();
//            return modelMapper.map(student,StudentDto.class);
//        }
//        throw new NotFoundException("khong co sinh vien co id="+id);
        Student student=findByid(id).orElseThrow(()->{
            throw new NotFoundException("khong co sinh vien co id="+id);
        });
        return modelMapper.map(student,StudentDto.class);
    }
    //helper method
    public Optional<Student> findByid(int id){
        return students.stream().filter(student -> student.getId()==id).findFirst();
    }

    public void deleteStudent(int id) {
        Optional<Student> studentOptional=findByid(id);
        if(studentOptional.isPresent()){
            Student student=studentOptional.get();
            students.remove(student);
            return;
        }
        throw new NotFoundException("khong co sinh vien co id="+id);
    }

    public List<StudentDto> searchStudent(String nameValue) {
        //Optional<List<Student>> studentOptional=students.stream().filter(student->student.getName().contains(nameValue.toLowerCase()))
        return students.stream()
                .filter(student->student.getName().toLowerCase().contains(nameValue.toLowerCase()))
                .map(student->modelMapper.map(student,StudentDto.class))
                .collect(Collectors.toList());
    }

    public StudentDto createStudent(CreateStudentRequest request) {
        if(findByEmail(request.getEmail()).isPresent()){
            throw new BadRequestException("Email = "+request.getEmail()+"da ton tai");
        }
        //Tao doi tuong student moi
        Random rd=new Random();
        Student student=new Student();
        student.setId(rd.nextInt(100));
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());
        //Them vao trong list
        students.add(student);
        //Tra ve doi tuong dto
        return modelMapper.map(student,StudentDto.class);
    }
    public Optional<Student> findByEmail(String email){
        return students.stream().filter(student -> student.getEmail().equals(email)).findFirst();
    }

    public StudentDto updateStudent(int id, UpdateStudentRequest request) {
        Optional<Student> studentOptional=findByid(id);
        if(studentOptional.isPresent()){
            Student student=studentOptional.get();
            student.setName(request.getName());
            student.setPassword(request.getPassword());
            return modelMapper.map(student,StudentDto.class);
        }
        throw new NotFoundException("Khong co sinh vien co id ="+id);
    }
}
