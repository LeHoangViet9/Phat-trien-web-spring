package com.session04.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CourseEnrollmentResponse {
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentAt;
}
