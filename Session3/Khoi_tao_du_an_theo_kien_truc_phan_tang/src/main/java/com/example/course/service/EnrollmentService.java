package com.example.course.service;

import com.example.course.models.Course;
import com.example.course.models.DTO.EnrollCourseRequest;
import com.example.course.models.DTO.EnrollmentDetail;
import com.example.course.models.Enrollment;
import com.example.course.models.Instructor;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.EnrollmentRepository;
import com.example.course.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Enrollment> getEnrollments(){
        return  enrollmentRepository.getEnrollments();

    }
    public Enrollment getEnrollment(Long id){
        return enrollmentRepository.getEnrollmentById(id).orElseThrow(()-> new RuntimeException("enrollment not found"));
    }
    public Enrollment addEnrollment(Enrollment enrollment){
        return enrollmentRepository.addEnrollment(enrollment);
    }
    public Enrollment updateEnrollment(Long id,Enrollment enrollment){
        return enrollmentRepository.updateEnrollment(id, enrollment);
    }
    public void deleteEnrollment(Long id){
        enrollmentRepository.deleteEnrollment(id);
    }

    public EnrollmentDetail getEnrollmentDetail(EnrollCourseRequest request){
        Course course=courseRepository.getCourseById(request.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found"));
        if(!course.getStatus().equalsIgnoreCase("ACTIVE")){
            throw new RuntimeException("Course not active");
        }
        Instructor instructor=instructorRepository.getInstructorById(course.getInstructorId()).orElseThrow(()-> new RuntimeException("Instructor not found"));
        Enrollment enrollment=new Enrollment();
        enrollment.setStudentName(request.getStudentName());
        enrollment.setCourseId(request.getCourseId());
        Enrollment savedEnrollment=enrollmentRepository.addEnrollment(enrollment);
        return new EnrollmentDetail(savedEnrollment.getId(), savedEnrollment.getStudentName(), course);

    }
}
