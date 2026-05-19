package com.session04.service;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.respose.CourseResponse;
import com.session04.model.entity.Student;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAll();
    CourseResponse  findById(long id);
    CourseResponse createCourse(CourseCreateRequest request);
//    CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request);
//    void dropoutStudent

    interface StudentRepository extends Repository<Student, Long> {
        Student getStudentsById(Long id);

        Student findStudentById(Long id);
    }
}
