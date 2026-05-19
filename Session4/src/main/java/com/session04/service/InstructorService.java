package com.session04.service;

import com.session04.model.dto.request.InstructorCreateRequest;
import com.session04.model.entity.Course;
import com.session04.model.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InstructorService {
    Instructor findStudentById(Long id);
    List<Instructor> findAll();
    Instructor createInstructor(InstructorCreateRequest request);
}
