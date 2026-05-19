package com.session04.controller;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.respose.CourseResponse;
import com.session04.model.entity.Course;
import com.session04.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @RequestBody CourseCreateRequest request
    ) {

        return ResponseEntity.ok(
                courseService.createCourse(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses() {

        return ResponseEntity.ok(
                courseService.findAll()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponse> getCourse(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                courseService.findById(id)
        );
    }
}
