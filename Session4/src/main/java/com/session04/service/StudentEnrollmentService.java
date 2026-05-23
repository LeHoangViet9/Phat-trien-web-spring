package com.session04.service;

import com.session04.model.dto.request.CourseEnrollmentRequest;
<<<<<<< HEAD
import com.session04.model.dto.response.CourseEnrollmentResponse;
import com.session04.model.dto.response.StudentResponse;
=======
import com.session04.model.dto.respose.CourseEnrollmentRespose;
import com.session04.model.dto.respose.StudentResponse;
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
import com.session04.model.entity.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentService {
    StudentEnrollment getStudentEnrollmentById(Long id);
    StudentEnrollment creStudentEnrollment( StudentEnrollment studentEnrollment);
<<<<<<< HEAD
    CourseEnrollmentResponse enrollStudent(Long courseId, CourseEnrollmentRequest request);
=======
    CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request);
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
    void dropoutStudent(Long courseId, Long studentId);
    List<StudentResponse> searchStudent(Long courseId, String search);


}
