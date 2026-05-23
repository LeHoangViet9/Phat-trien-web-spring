package com.session04.service;

import com.session04.model.dto.request.CourseCreateRequest;
<<<<<<< HEAD
import com.session04.model.dto.response.CourseResponse;
import com.session04.model.dto.response.CourseResponseV2;
import com.session04.model.dto.response.PageResponse;
import com.session04.model.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
=======
import com.session04.model.dto.respose.CourseResponse;
import com.session04.model.entity.Student;
import org.springframework.data.repository.Repository;
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAll();
    CourseResponse  findById(long id);
<<<<<<< HEAD
//    CourseResponse createCourse(CourseCreateRequest request);
      CourseResponseV2 createCourse(CourseCreateRequest request);
//    CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request);
//    void dropoutStudent
//    Page<CourseResponse> getAllCourse(Integer page, Integer size, String sortBy, Sort.Direction direction);
    PageResponse<CourseResponse> findAllByPage(Integer page, Integer size, String sortBy, Sort.Direction direction);

    PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status);

    Page<CourseResponse>getAllCourse(CourseStatus status,String keyword,Integer page,Integer size,String sortBy,Sort.Direction direction);

=======
    CourseResponse createCourse(CourseCreateRequest request);
//    CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request);
//    void dropoutStudent

    interface StudentRepository extends Repository<Student, Long> {
        Student getStudentsById(Long id);

        Student findStudentById(Long id);
    }
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
}
