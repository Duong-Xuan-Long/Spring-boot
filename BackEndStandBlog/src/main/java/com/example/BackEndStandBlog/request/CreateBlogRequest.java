package com.example.BackEndStandBlog.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBlogRequest {
    private String title;
    private String content;
    private String description;
    private Boolean status;
    private List<Integer> categoryIds;
}
