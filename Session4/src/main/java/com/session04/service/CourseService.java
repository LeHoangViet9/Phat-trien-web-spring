package com.session04.service;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.response.CourseResponse;
import com.session04.model.dto.response.CourseResponseV2;
import com.session04.model.dto.response.PageResponse;
import com.session04.model.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAll();
    CourseResponse  findById(long id);
//    CourseResponse createCourse(CourseCreateRequest request);
      CourseResponseV2 createCourse(CourseCreateRequest request);
//    CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request);
//    void dropoutStudent
//    Page<CourseResponse> getAllCourse(Integer page, Integer size, String sortBy, Sort.Direction direction);
    PageResponse<CourseResponse> findAllByPage(Integer page, Integer size, String sortBy, Sort.Direction direction);

    PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status);

    Page<CourseResponse>getAllCourse(CourseStatus status,String keyword,Integer page,Integer size,String sortBy,Sort.Direction direction);

}
