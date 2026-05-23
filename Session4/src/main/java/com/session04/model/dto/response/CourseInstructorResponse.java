package com.session04.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseInstructorResponse {
    private Long id;
    private String name;
}
