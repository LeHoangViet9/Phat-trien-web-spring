package com.session04.service.impl;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.respose.CourseInstructorResponse;
import com.session04.model.dto.respose.CourseResponse;
import com.session04.model.entity.Course;
import com.session04.model.entity.Instructor;
import com.session04.repository.CourseRepository;
import com.session04.repository.InstructorRepository;
import com.session04.service.CourseService;
import lombok.RequiredArgsConstructor;
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
    public CourseResponse createCourse(CourseCreateRequest request) {

        Instructor instructor = instructorRepository
                .findById(request.getInstructorId())
                .orElseThrow(() ->
                        new RuntimeException("Can not found")
                );

        Course course = Course.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return mapToResponse(course);
    }

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
