package com.example.BackEndStandBlog.utils;

import com.example.BackEndStandBlog.dto.BlogDto;

import java.util.List;
import java.util.Set;

public class Utils {
    public static String generateCategoryString(Set<BlogDto.CategoryDto> categories){
        List<String> categoryNames=categories.stream()
                .map(blogDto->blogDto.getName()).toList();
        String[] arrNames=categoryNames.toArray(new String[0]);
        return String.join(".",arrNames);
    }
}
