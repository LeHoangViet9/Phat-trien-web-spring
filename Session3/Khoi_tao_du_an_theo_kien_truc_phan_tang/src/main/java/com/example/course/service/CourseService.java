package com.example.course.service;

import com.example.course.models.Course;
import com.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public List<Course> getCourses(){
      return   courseRepository.getCourses();
    }
    public Course getCourseById(Long id){
        return courseRepository.getCourseById(id).orElseThrow(()-> new RuntimeException("course not found"));
    }
    public Course addCourse(Course course){
        return courseRepository.addCourse(course);
    }
    public Course updateCourse(Long id,Course course){
        return courseRepository.updateCourse(id, course);
    }
    public void deleteCourse(Long id){
        courseRepository.deleteCourse(id);
    }
}
