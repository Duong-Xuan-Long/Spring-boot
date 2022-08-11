package com.example.coursesample.service;

import com.example.coursesample.database.FakeDB;
import com.example.coursesample.model.Course;
import com.example.coursesample.model.Supporter;
import com.example.coursesample.pair.Pair;
import com.example.coursesample.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return courseRepository.getAll();
    }
    public List<Course> getTypeCourses(String type){
        return courseRepository.getTypeCourses(type);
    }
    public List<Course> findTitleTopics(List<Course> list,Optional<String> topic, Optional<String> title){
        return courseRepository.findTitleTopics(list,topic,title);
    }
    public Pair showDetailedCourse(int id){
        return courseRepository.showDetailedCourse(id);
    }
}
