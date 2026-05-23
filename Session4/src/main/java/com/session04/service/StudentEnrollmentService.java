package com.session04.service;

import com.session04.model.dto.request.CourseEnrollmentRequest;
import com.session04.model.dto.response.CourseEnrollmentResponse;
import com.session04.model.dto.response.StudentResponse;
import com.session04.model.entity.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentService {
    StudentEnrollment getStudentEnrollmentById(Long id);
    StudentEnrollment creStudentEnrollment( StudentEnrollment studentEnrollment);
    CourseEnrollmentResponse enrollStudent(Long courseId, CourseEnrollmentRequest request);
    void dropoutStudent(Long courseId, Long studentId);
    List<StudentResponse> searchStudent(Long courseId, String search);


}
