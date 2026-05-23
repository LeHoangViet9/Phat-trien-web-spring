package com.session04.service.impl;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.response.CourseInstructorResponse;
import com.session04.model.dto.response.CourseResponse;
import com.session04.model.dto.response.CourseResponseV2;
import com.session04.model.dto.response.PageResponse;
import com.session04.model.entity.Course;
import com.session04.model.entity.CourseStatus;
import com.session04.model.entity.Instructor;
import com.session04.repository.CourseRepository;
import com.session04.repository.InstructorRepository;
import com.session04.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<CourseResponse> findAll() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CourseResponse findById(long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Can not found course")
                );

        return mapToResponse(course);
    }

    @Override
    public CourseResponseV2 createCourse(CourseCreateRequest request) {
        Instructor instructor=instructorRepository.findById(request.getInstructorId()).orElseThrow(()->new RuntimeException("Can not found instructor"));
        Course course=Course.builder().title(request.getTitle()).status(request.getStatus()).instructor(instructor).build();
        courseRepository.save(course);
        return CourseResponseV2.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus()).build();
    }

//    @Override
//    public CourseResponse createCourse(CourseCreateRequest request) {
//
//        Instructor instructor = instructorRepository
//                .findById(request.getInstructorId())
//                .orElseThrow(() ->
//                        new RuntimeException("Can not found")
//                );
//
//        Course course = Course.builder()
//                .title(request.getTitle())
//                .status(request.getStatus())
//                .instructor(instructor)
//                .build();
//
//        courseRepository.save(course);
//
//        return mapToResponse(course);
//    }

    @Override
    public PageResponse<CourseResponse> findAllByPage(Integer page, Integer size, String sortBy, Sort.Direction direction) {
        if(page<0){
            page = 0;
        }
        if(sortBy==null){
            sortBy = "id";
        }
        Sort sort = Sort.by(direction,sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Course> coursePage = courseRepository.findAll(pageable);
        Page<CourseResponse> pageResponse = coursePage.map(this::mapToResponse);
        return PageResponse.<CourseResponse>builder()
                .items(pageResponse.getContent())
                .page(pageResponse.getNumber())
                .size(pageResponse.getSize())
                .totalItems(pageResponse.getTotalElements())
                .totalPages(pageResponse.getTotalPages())
                .isLast(pageResponse.isLast()).build();
    }
    // Áp dụng JPQL và Pageable để phân trang theo điều kiện
    @Override
    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        if(page<0){
            page = 0;
        }
        if(sortBy==null){
            sortBy = "id";
        }
        Sort sort = Sort.by(direction,sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Course> coursePage = courseRepository.findAllByStatus(status,pageable);
        Page<CourseResponse> courseResponsePageResponse=coursePage.map(this::mapToResponse);
        return PageResponse.<CourseResponse>builder()
                .items(courseResponsePageResponse.getContent())
                .page(courseResponsePageResponse.getNumber())
                .size(courseResponsePageResponse.getSize())
                .totalItems(courseResponsePageResponse.getTotalElements())
                .totalPages(courseResponsePageResponse.getTotalPages())
                .isLast(courseResponsePageResponse.isLast())
                .build();
    }
    // Nâng cấp API danh sách khóa học
    @Override
    public Page<CourseResponse> getAllCourse(CourseStatus status, String keyword, Integer page, Integer size, String sortBy, Sort.Direction direction) {
        if(page<0){
            page = 0;
        }
        Pageable pageable;
        if(sortBy==null||sortBy.isBlank()||direction==null||direction==null){
            pageable=PageRequest.of(page,size,Sort.by(direction,sortBy));

        }else{
            Sort sort=Sort.by(direction,sortBy);
            pageable=PageRequest.of(page,size,sort);
        }
        Page<Course> coursePage = courseRepository.searchCourse(status,keyword,pageable);

        return coursePage.map(this::mapToResponse);
    }

//    @Override
//    public Page<CourseResponse> getAllCourse(
//            Integer page,
//            Integer size,
//            String sortBy,
//            Sort.Direction direction
//    ) {
//
//        if (page < 0) {
//            page = 0;
//        }
//
//        if (sortBy == null || sortBy.isBlank()) {
//            sortBy = "id";
//        }
//
//        Sort sort = Sort.by(direction, sortBy);
//
//        Pageable pageable =
//                PageRequest.of(page, size, sort);
//        Page<Course> coursePage =
//                courseRepository.findAll(pageable);
//        return coursePage.map(this::mapToResponse);
//    }


    private CourseResponse mapToResponse(Course course) {

        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .instructor(
                        CourseInstructorResponse.builder()
                                .id(course.getInstructor().getId())
                                .name(course.getInstructor().getName())
                                .build()
                )
                .build();
    }



}
