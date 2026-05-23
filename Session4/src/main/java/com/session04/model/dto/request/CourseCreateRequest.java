package com.session04.model.dto.request;

import com.session04.model.entity.CourseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
