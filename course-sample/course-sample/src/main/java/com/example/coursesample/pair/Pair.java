package com.example.coursesample.pair;

import com.example.coursesample.model.Course;
import com.example.coursesample.model.Supporter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pair {
    private Course course;
    private Supporter supporter;
}
