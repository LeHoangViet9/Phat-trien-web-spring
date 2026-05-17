package com.example.course.service;

import com.example.course.models.Course;
import com.example.course.models.DTO.IntructorDetail;
import com.example.course.models.Instructor;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.EnrollmentRepository;
import com.example.course.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<Instructor> getInstructors(){
        return  instructorRepository.getInstructors();
    }
    public Instructor getInstructor(Long id){
        return instructorRepository.getInstructorById(id).orElseThrow(() -> new RuntimeException("instructor not found"));
    }
    public Instructor addInstructor(Instructor instructor){
        return instructorRepository.addInstructor(instructor);
    }
    public Instructor updateInstructor(Long id,Instructor instructor){
        return instructorRepository.updateInstructor(id,instructor);
    }
    public void deleteInstructor(Long id){
         instructorRepository.deleteInstructor(id);
    }

    public List<IntructorDetail> getInstructorDetail(){

        List<Instructor> instructors = instructorRepository.getInstructors();

        return instructors.stream().map(instructor -> {

            List<Course> validCourse = courseRepository
                    .getCourseByInstructorId(instructor.getId())
                    .stream()
                    .filter(c ->
                            c.getStatus().equalsIgnoreCase("ACTIVE")
                                    &&
                                    enrollmentRepository.existByCourseId(
                                            Long.valueOf(c.getId())
                                    )
                    )
                    .toList();

            return new IntructorDetail(
                    instructor.getId(),
                    instructor.getEmail(),
                    validCourse
            );

        }).toList();
    }
}
