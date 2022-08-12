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
    @GetMapping({"/courses/{type}","/courses"})
    public List<Course> getAllCourse(@PathVariable Optional<String> type, @RequestParam Optional<String> topic, @RequestParam Optional<String> title) {
        if(!type.isPresent()){
            return courseService.findTitleTopics(courseService.getAll(),topic,title);
        }
        else{
            String typeString=type.get();
            return courseService.findTitleTopics(courseService.getTypeCourses(type.get()),topic,title);
        }
    }
    @GetMapping("/course-detail/{id}")
    public Pair showDetailCourse(@PathVariable int id ){
        return courseService.showDetailedCourse(id);
    }
}
