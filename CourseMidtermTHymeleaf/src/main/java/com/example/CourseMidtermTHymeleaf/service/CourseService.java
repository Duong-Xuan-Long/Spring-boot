package com.example.CourseMidtermTHymeleaf.service;

import com.example.CourseMidtermTHymeleaf.database.FakeDB;
import com.example.CourseMidtermTHymeleaf.exception.NotFoundException;
import com.example.CourseMidtermTHymeleaf.model.Course;
import com.example.CourseMidtermTHymeleaf.model.Supporter;
import com.example.CourseMidtermTHymeleaf.repository.CourseRepository;
import com.example.CourseMidtermTHymeleaf.request.CreateCourseRequest;
import com.example.CourseMidtermTHymeleaf.request.UpdateCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FileService fileService;

    public Page<Course> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Course> list;

        if (FakeDB.courses.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, FakeDB.courses.size());
            list = FakeDB.courses.subList(startItem, toIndex);
        }

        Page<Course> coursePage
                = new PageImpl<Course>(list, PageRequest.of(currentPage, pageSize), FakeDB.courses.size());

        return coursePage;
    }
    public Object getAll() {
        return courseRepository.getAll();
    }

    public Object createCourse(CreateCourseRequest createCourseRequest) {
        return courseRepository.createCourse(createCourseRequest);
    }

    public Object updateCourse(UpdateCourseRequest updateCourseRequest, int id) {
        return courseRepository.updateRequest(updateCourseRequest,id);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteRequest(id);
    }

    public String uploadFile(int id, MultipartFile file) {
        Course course=courseRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        String filePath= fileService.uploadFile(file);

        course.setImage(filePath);
        return filePath;
    }

    public byte[] readFile(String fileId) {
        return fileService.readFile(fileId);
    }

    public List<Supporter> getAllSupporter() {
        return courseRepository.getAllSupporter();
    }

    public Course getCourseId(int id) {
        Course course=courseRepository.findByid(id).orElseThrow(()->{
            throw  new NotFoundException("khong tim thay user co id ="+id);
        });
        return course;
    }
}
