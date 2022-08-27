package com.example.templatecourseadmin.repository;

import com.example.templatecourseadmin.database.FakeDB;
import com.example.templatecourseadmin.exception.NotFoundException;
import com.example.templatecourseadmin.model.Course;
import com.example.templatecourseadmin.model.Supporter;
import com.example.templatecourseadmin.request.CreateCourseRequest;
import com.example.templatecourseadmin.request.UpdateCourseRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class CourseRepository {
    @Autowired
    private ModelMapper modelMapper;
    public List<Course> getAll() {
        return FakeDB.courses;
    }

    public Course createCourse(CreateCourseRequest createCourseRequest) {
        Random rd=new Random();
        Course course=new Course(rd.nextInt(1000), createCourseRequest.getTitle(), createCourseRequest.getDescription(),
                createCourseRequest.getType(),null, createCourseRequest.getTopics(), createCourseRequest.getSupporterId());
        FakeDB.courses.add(0,course);
        return course;
    }

    public Course updateRequest(UpdateCourseRequest updateCourseRequest, int id) {
        Course course=findByid(id).orElseThrow(()->{
            throw new NotFoundException("khong tim thay khoa hoc co id la "+id);
        });
        course.setTitle(updateCourseRequest.getTitle());
        course.setDescription(updateCourseRequest.getDescription());
        course.setType(updateCourseRequest.getType());
        course.setTopics(updateCourseRequest.getTopics());
        course.setSupporterId(updateCourseRequest.getSupporterId());
        return course;
    }

    public void deleteRequest(int id) {
        Course course=findByid(id).orElseThrow(()->{
            throw new NotFoundException("khong tim thay khoa hoc co id la "+id);
        });
        FakeDB.courses.removeIf(c->(c.getId()==id));
    }
    public Optional<Course> findByid(int id) {
        return FakeDB.courses.stream().filter(course -> course.getId()==id).findFirst();
    }

    public List<Supporter> getAllSupporter() {
        return FakeDB.supporters;
    }
}
