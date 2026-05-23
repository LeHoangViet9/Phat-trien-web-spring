package com.session04.controller;

import com.session04.model.entity.StudentEnrollment;
import com.session04.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/studentenrollments")
@RequiredArgsConstructor
public class StudentEnrollmentController {
    private final StudentEnrollmentService studentEnrollmentService;
    @PostMapping
    public StudentEnrollment saveStudentEnrollment(@RequestBody StudentEnrollment studentEnrollment) {
        return studentEnrollmentService.creStudentEnrollment(studentEnrollment);
    }
    @GetMapping("{id}")
    public StudentEnrollment getAllStudentEnrollmentById(@PathVariable  Long id) {
        return studentEnrollmentService.getStudentEnrollmentById(id);
    }
}
