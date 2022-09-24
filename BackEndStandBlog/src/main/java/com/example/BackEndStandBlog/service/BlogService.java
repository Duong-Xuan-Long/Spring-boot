package com.example.BackEndStandBlog.service;

import com.example.BackEndStandBlog.dto.BlogDto;
import com.example.BackEndStandBlog.dto.BlogInfo;
import com.example.BackEndStandBlog.entity.Blog;
import com.example.BackEndStandBlog.entity.Category;
import com.example.BackEndStandBlog.entity.User;
import com.example.BackEndStandBlog.repository.BlogRepository;
import com.example.BackEndStandBlog.repository.CategoryRepository;
import com.example.BackEndStandBlog.repository.UserRepository;
import com.example.BackEndStandBlog.request.CreateBlogRequest;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public List<BlogDto> getAllBlogDto(){
        List<Blog> blogs=blogRepository.findAll();
        return blogs.stream()
                .map(blog->modelMapper.map(blog,BlogDto.class))
                .sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }
    public List<BlogDto> getALlBlogDtoByUserId(Integer id){
        List<Blog> blogs=blogRepository.getByUser_IdOrderByCreatedAtDesc(id);
        return blogs.stream()
                .map(blog->modelMapper.map(blog,BlogDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public BlogDto createBlog(Integer userId,CreateBlogRequest createBlogRequest) {
        User user=userRepository.getById(userId);
        //Lay ra danh sach category dua vao mang id tu request
        Set<Category> categories=categoryRepository.getByIdIn(createBlogRequest.getCategoryIds());
        Blog blog=Blog.builder()
                .title(createBlogRequest.getTitle())
                .description(createBlogRequest.getDescription())
                .content(createBlogRequest.getContent())
                .status(createBlogRequest.getStatus())
                .categories(categories)
                .user(user)
                .build();
        blogRepository.save(blog);
        return modelMapper.map(blog,BlogDto.class);
    }
    public List<BlogInfo> getAllBlogInfo(){
        return blogRepository.getAllBlogInfo();
    }

    public Object getAllBlogInfoById(Integer id) {
        return blogRepository.getAllBlogInfo().stream().filter(b->b.getId()==id).findFirst();
    }
}
