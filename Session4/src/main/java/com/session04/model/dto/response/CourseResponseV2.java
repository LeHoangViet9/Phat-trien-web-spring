package com.session04.model.dto.response;

import com.session04.model.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
