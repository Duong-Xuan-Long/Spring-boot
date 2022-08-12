package com.example.coursesample;

import com.example.coursesample.database.FakeDB;
import com.example.coursesample.model.Course;
import com.example.coursesample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class test {
    public static void main(String[] args) {
        CourseService courseService=new CourseService();
        List<Course> list =courseService.findTitleTopics(courseService.getTypeCourses("online"),null,null);
        System.out.println(list);
    }
}
