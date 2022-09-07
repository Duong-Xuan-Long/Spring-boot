package com.example.CourseMidtermTHymeleaf.controller;

import com.example.CourseMidtermTHymeleaf.request.CreateCourseRequest;
import com.example.CourseMidtermTHymeleaf.request.UpdateCourseRequest;
import com.example.CourseMidtermTHymeleaf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    private CourseService courseService;
    //Lay danh sach tat ca cac khoa hoc
    @GetMapping("/courses")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(courseService.getAll());
    }
    //Lay khoa hoc theo id
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseId(@PathVariable int id){
        return ResponseEntity.ok(courseService.getCourseId(id));
    }
    //Tao khoa hoc
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest){
        return ResponseEntity.ok(courseService.createCourse(createCourseRequest));
    }
    //Cap nhat khoa hoc
    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody UpdateCourseRequest updateCourseRequest, @PathVariable int id){
        return ResponseEntity.ok(courseService.updateCourse(updateCourseRequest,id));
    }
    //Xoa khoa hoc
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    //up file
    @PostMapping("/courses/{id}/files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable int id){
        String filePath= courseService.uploadFile(id,file);
        return ResponseEntity.ok(filePath);
    }
    //Xem file
    @GetMapping("/files/{fileId}")//produces={MediaType.IMAGE_JPEG_VALUE}
    public ResponseEntity<?> readFile(@PathVariable String fileId){
        byte[] bytes=courseService.readFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    @GetMapping("/supporters")
    public ResponseEntity<?> getAllSupporters(){
        return ResponseEntity.ok(courseService.getAllSupporter());
    }
}
