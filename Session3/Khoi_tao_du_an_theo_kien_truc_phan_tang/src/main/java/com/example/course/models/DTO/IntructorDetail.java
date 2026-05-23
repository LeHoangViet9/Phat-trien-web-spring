package com.example.course.models.DTO;

import com.example.course.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntructorDetail {
    private Long id;
    private String email;
    private List<Course> courseList;
}
