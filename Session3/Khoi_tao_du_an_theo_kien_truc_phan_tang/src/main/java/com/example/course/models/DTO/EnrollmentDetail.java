package com.example.course.models.DTO;

import com.example.course.models.Course;
import com.example.course.models.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDetail {
    private Long id;
    private String studentName;
    private Course course;

}
