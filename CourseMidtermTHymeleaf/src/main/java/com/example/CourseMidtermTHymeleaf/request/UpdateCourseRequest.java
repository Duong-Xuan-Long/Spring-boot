package com.example.CourseMidtermTHymeleaf.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseRequest {
    private String title;
    private String description;
    private String type;
    private List<String> topics;
    private int supporterId;
}
