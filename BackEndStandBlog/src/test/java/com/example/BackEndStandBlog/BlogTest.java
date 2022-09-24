package com.example.BackEndStandBlog;

import com.example.BackEndStandBlog.dto.BlogDto;
import com.example.BackEndStandBlog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogTest {
    @Autowired
    private BlogService blogService;

    @Test
    void get_all_blog_dto_test(){
        List<BlogDto> blogDtos=blogService.getAllBlogDto();
        blogDtos.forEach(blog-> System.out.println(blog.toString()));
    }
}
