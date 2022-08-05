package com.example.student.controller;

import com.example.student.dto.StudentDto;
import com.example.student.model.Student;
import com.example.student.request.CreateStudentRequest;
import com.example.student.request.UpdateStudentRequest;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// HTTP method : GET, POST, PUT, DELETE
// GET /api//v1/students/ : Lay ra danh sach cac sinh vien
// POST /api//v1/students/ : Tao moi sinh vien cac sinh vien
// GET /api//v1/students/{id} : Lay thong tin cua sinh vien theo id
// PUT /api//v1/students/{id} : Cap nhat thong tin sinh vien theo id
// DELETE/api/v1/students/{id}:xoa sinh vien theo id
// GET /api//v1/students/search?name=nguyenvana : Tim kiem sinh vien theo ten

// GET /api//v1/students/{id}/courses : Lay ra danh sach tat ca cac khoa hoc cua sinh vien
// POST /api//v1/students/{id}/courses : Tao moi cac khoa hoc cua sinh vien
                                    @RestController
                                    @RequestMapping("api/v1")
public class StudentController {
        @Autowired
        private StudentService studentService;

        //Lay danh sach tat ca cac sinh vien
        @GetMapping("/students")
        public List<StudentDto> getALlStudent(){
            return studentService.getAll();
        }

        //lay thong tin sinh vien theo id
        @GetMapping("/students/{id}")
        public StudentDto getStudentById(@PathVariable int id){
            return studentService.getStudentByid(id);
        }

        @DeleteMapping("/students/{id}")
        public void deleteStudent(@PathVariable int id){
            studentService.deleteStudent(id);
        }
        @GetMapping("/students/search")
        public List<StudentDto> searchStudent(@RequestParam(name="name") String nameValue){
            return studentService.searchStudent(nameValue);
        }
        @PostMapping("/students")
        public StudentDto searchStudent(@RequestBody CreateStudentRequest request){
        return studentService.createStudent(request);
    }
    @PutMapping("/students/{id}")
    public StudentDto updateStudent(@PathVariable int id, @RequestBody UpdateStudentRequest request){
        return studentService.updateStudent(id,request);
    }
}
