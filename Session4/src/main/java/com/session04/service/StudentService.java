package com.session04.service;

import com.session04.model.dto.request.InstructorCreateRequest;
import com.session04.model.entity.Instructor;
import com.session04.model.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    List<Student>  findAllStudents();
    Student getStudentById(Long id);
}
