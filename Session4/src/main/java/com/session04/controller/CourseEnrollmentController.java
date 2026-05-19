package com.session04.controller;

import com.session04.model.dto.request.CourseEnrollmentRequest;
import com.session04.model.dto.respose.CourseEnrollmentRespose;
import com.session04.model.dto.respose.StudentResponse;
import com.session04.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseEnrollmentController {
    private final StudentEnrollmentService studentEnrollmentService;
    @PostMapping("{courseId}/enrollments")
    public ResponseEntity<CourseEnrollmentRespose>
    enrollStudent(
            @PathVariable Long courseId,
            @RequestBody CourseEnrollmentRequest request
    ) {

        return ResponseEntity.ok(
                studentEnrollmentService
                        .enrollStudent(courseId, request)
        );
    }

    @DeleteMapping(
            "{courseId}/students/{studentId}"
    )
    public ResponseEntity<Void> dropoutStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {

        studentEnrollmentService
                .dropoutStudent(courseId, studentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(
            "{courseId}/enrollments/students"
    )
    public ResponseEntity<List<StudentResponse>>
    searchStudents(
            @PathVariable Long courseId,
            @RequestParam String search
    ) {

        return ResponseEntity.ok(
                studentEnrollmentService
                        .searchStudent(courseId, search)
        );
    }
}
