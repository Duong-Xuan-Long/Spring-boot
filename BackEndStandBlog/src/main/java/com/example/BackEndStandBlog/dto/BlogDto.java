package com.example.BackEndStandBlog.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogDto implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private LocalDateTime createdAt;
    private Boolean status;
    private UserDto user;
    private Set<CategoryDto> categories = new LinkedHashSet<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class UserDto implements Serializable {
        private Integer id;
        private String name;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class CategoryDto implements Serializable {
        private Integer id;
        private String name;
    }
}
