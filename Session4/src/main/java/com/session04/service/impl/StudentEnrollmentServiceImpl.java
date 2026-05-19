package com.session04.service.impl;

import com.session04.model.dto.request.CourseEnrollmentRequest;
import com.session04.model.dto.respose.CourseEnrollmentRespose;
import com.session04.model.dto.respose.StudentResponse;
import com.session04.model.entity.Course;
import com.session04.model.entity.CourseStatus;
import com.session04.model.entity.Student;
import com.session04.model.entity.StudentEnrollment;
import com.session04.repository.CourseRepository;
import com.session04.repository.StudentEnrollmentRepository;
import com.session04.repository.StudentRepository;
import com.session04.service.CourseService;
import com.session04.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public StudentEnrollment getStudentEnrollmentById(Long id) {
        return studentEnrollmentRepository.findById(id).orElseThrow(()->new RuntimeException("Can not found"));
    }

    @Override
    public StudentEnrollment creStudentEnrollment( StudentEnrollment studentEnrollment) {
        return studentEnrollmentRepository.save(studentEnrollment);
    }

    @Override
    public CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request) {
        boolean activeCourse=courseRepository.existsByIdAndStatus(courseId, CourseStatus.ACTIVE);
        if(!activeCourse){
            throw new RuntimeException("Course is not ACTIVE");
        }
        boolean existed= studentEnrollmentRepository.existsByCourseIdAndStudentId(courseId, request.getStudentId());
        if(existed){
            throw new RuntimeException("Student already enrolled");
        }
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Can not found"));

        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(()->new RuntimeException("Student not found"));
        StudentEnrollment studentEnrollment=StudentEnrollment.builder().course(course).student(student).enrollmentDate(LocalDateTime.now()).build();
        studentEnrollmentRepository.save(studentEnrollment);
        return CourseEnrollmentRespose.builder().
                courseId(courseId).
                studentId(student.getId()).
                enrollmentAt(LocalDate.from(LocalDateTime.now())).build();



    }

    @Override
    public void dropoutStudent(Long courseId, Long studentId)

    {

            boolean existed =
                    studentEnrollmentRepository
                            .existsByCourseIdAndStudentId(
                                    courseId,
                                    studentId
                            );

            if (!existed) {
                throw new RuntimeException(
                        "Enrollment not found"
                );
            }

            studentEnrollmentRepository
                    .deleteByStudentIdAndCourseId(
                            studentId,courseId
                    );
    }

    @Override
    public List<StudentResponse> searchStudent(Long courseId, String search) {
        return studentEnrollmentRepository
                .findByCourseIdAndStudentStuNameContainingIgnoreCase(
                        courseId,
                        search
                )
                .stream()
                .map(enrollment ->
                        StudentResponse.builder()
                                .id(enrollment.getStudent().getId())
                                .name(enrollment.getStudent().getStuName())
                                .email(enrollment.getStudent().getEmail())
                                .build()
                )
                .toList();
    }
}
