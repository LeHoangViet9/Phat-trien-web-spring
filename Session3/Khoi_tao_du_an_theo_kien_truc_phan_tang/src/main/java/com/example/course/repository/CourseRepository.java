package com.example.course.repository;

import com.example.course.models.Course;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private List<Course> courses;
    public CourseRepository() {
        courses = new ArrayList<>();
        courses.add(new Course(1L, "Java Core", "OPEN", 1L));
        courses.add(new Course(2L,"Spring Boot", "OPEN", 2L));

    }
    public List<Course> getCourses() {
        return courses;
    }
    public Optional<Course> getCourseById(Long id){
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }


    public Course addCourse(Course course){
        courses.add(course);
        return course;
    }
    public Course updateCourse(Long id,Course course){
        Course existingCourse = getCourseById(id).orElseThrow(()-> new RuntimeException("course not found"));
        existingCourse.setTitle(course.getTitle());
        existingCourse.setStatus(course.getStatus());
        existingCourse.setInstructorId(course.getInstructorId());
        return  existingCourse;
    }
    public void deleteCourse(Long id){
        Course existingCourse = getCourseById(id).orElseThrow(()-> new RuntimeException("course not found"));
        courses.remove(existingCourse);
    }

    public List<Course> getCourseByInstructorId(Long instructorId){
        return courses.stream().filter(c -> c.getInstructorId().equals(instructorId)).toList();
    }
}
