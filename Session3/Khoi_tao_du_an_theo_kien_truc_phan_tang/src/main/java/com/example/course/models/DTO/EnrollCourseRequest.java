package com.example.course.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollCourseRequest {
    private Long id;
    private String studentName;
    private Long courseId;
}
