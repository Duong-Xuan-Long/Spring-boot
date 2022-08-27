package com.example.templatecourseadmin.controller;

import com.example.templatecourseadmin.model.Course;
import com.example.templatecourseadmin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ViewController {
    @Autowired
    private CourseService courseService;

//    @GetMapping("/")
//    public String getListPage(Model model){
//        model.addAttribute("courses",courseService.getAll());
//        return "course-list";
//    }

    @GetMapping("/create")
    public String getCreatePage(){
        return "course-create";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(Model model, @PathVariable int id){
        model.addAttribute("course",courseService.getCourseId(id));
        return "course-edit";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Course> coursePage = courseService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("coursePage", coursePage);

        int totalPages = coursePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "course-list";
    }
}
