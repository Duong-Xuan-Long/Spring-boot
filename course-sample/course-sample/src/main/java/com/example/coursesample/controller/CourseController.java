package com.example.coursesample.controller;

import com.example.coursesample.model.Course;
import com.example.coursesample.pair.Pair;
import com.example.coursesample.service.CourseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    @GetMapping("/courses")
    public List<Course> getAllCourse(@RequestParam Optional<String> type) {
        if(type.isPresent()){
            return courseService.getTypeCourses(type.get());
        }
        return courseService.getAll();
    }
    @GetMapping("/courses/{type}")
    public List<Course> findTitleTopics(@PathVariable String type, @RequestParam Optional<String> topic, @RequestParam Optional<String> title){
        return courseService.findTitleTopics(courseService.getTypeCourses(type),topic,title);
    }
    @GetMapping("/course-detail/{id}")
    public Pair showDetailCourse(@PathVariable int id ){
        return courseService.showDetailedCourse(id);
    }
}
