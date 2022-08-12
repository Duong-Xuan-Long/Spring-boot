package com.example.coursesample.repository;

import com.example.coursesample.database.FakeDB;
import com.example.coursesample.model.Course;
import com.example.coursesample.model.Supporter;
import com.example.coursesample.pair.Pair;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    // Repository : Chứa các phương thức để thao tác với database

    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return FakeDB.courses;
    }
    public List<Course> getTypeCourses(String type){
        return FakeDB.courses.stream().filter(course->course.getType().equals(type)).collect(Collectors.toList());
    }
    public List<Course> findTitleTopics(List<Course> list,Optional<String> topic, Optional<String> title){
        if(topic.isPresent()&&title.isPresent()){
            return list.stream().filter(course->course.getTopics().contains(topic.get())&&check(course.getTitle(),title.get())).collect(Collectors.toList());
        }
        else if(topic.isPresent()){
            return list.stream().filter(course->course.getTopics().contains(topic.get())).collect(Collectors.toList());
        }
        else if(title.isPresent()){
            return list.stream().filter(course->check(course.getTitle(),title.get())).collect(Collectors.toList());
        }
        else {
            return list;
        }
    }
    public boolean check(String title,String k){
        String titleLowercase=title.toLowerCase();
        String kLowercase=k.toLowerCase();
        for(int i=0;i<titleLowercase.length();i++){
            for(int j=i+1;j<=titleLowercase.length();j++){
                if(titleLowercase.substring(i,j).contains(kLowercase)){
                    return true;
                }
            }
        }
        return false;
    }
    public Pair showDetailedCourse(int id){
        Course course=FakeDB.courses.stream().filter(c->c.getId()==id).collect(Collectors.toList()).get(0);
        Supporter supporter=FakeDB.supporters.stream().filter(s->s.getId()==course.getSupporterId()).collect(Collectors.toList()).get(0);
        return new Pair(course,supporter);
    }
}
