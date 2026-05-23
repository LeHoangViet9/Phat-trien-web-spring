package com.session04.model.dto.respose;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CourseEnrollmentRespose {
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentAt;
}
