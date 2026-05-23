package com.session04.model.dto.respose;

import com.session04.model.entity.CourseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}
