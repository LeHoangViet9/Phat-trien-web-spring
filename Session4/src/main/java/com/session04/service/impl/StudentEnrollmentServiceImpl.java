package com.session04.service.impl;

import com.session04.model.dto.request.CourseEnrollmentRequest;
<<<<<<< HEAD
import com.session04.model.dto.response.CourseEnrollmentResponse;
import com.session04.model.dto.response.StudentResponse;
=======
import com.session04.model.dto.respose.CourseEnrollmentRespose;
import com.session04.model.dto.respose.StudentResponse;
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
import com.session04.model.entity.Course;
import com.session04.model.entity.CourseStatus;
import com.session04.model.entity.Student;
import com.session04.model.entity.StudentEnrollment;
import com.session04.repository.CourseRepository;
import com.session04.repository.StudentEnrollmentRepository;
import com.session04.repository.StudentRepository;
<<<<<<< HEAD
import com.session04.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import com.session04.service.CourseService;
import com.session04.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e

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
<<<<<<< HEAD
    public CourseEnrollmentResponse enrollStudent(Long courseId, CourseEnrollmentRequest request) {
=======
    public CourseEnrollmentRespose enrollStudent(Long courseId, CourseEnrollmentRequest request) {
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
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
<<<<<<< HEAD
        StudentEnrollment enrollment = StudentEnrollment.builder()
                .course(course)
                .student(student)
                .enrollmentDate(LocalDateTime.now()) // Viết ngắn gọn thế này
                .build();

        studentEnrollmentRepository.save(enrollment);
        return CourseEnrollmentResponse.builder().
=======
        StudentEnrollment studentEnrollment=StudentEnrollment.builder().course(course).student(student).enrollmentDate(LocalDateTime.now()).build();
        studentEnrollmentRepository.save(studentEnrollment);
        return CourseEnrollmentRespose.builder().
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
                courseId(courseId).
                studentId(student.getId()).
                enrollmentAt(LocalDate.from(LocalDateTime.now())).build();



    }

    @Override
<<<<<<< HEAD
    @Transactional // Bắt buộc phải có để không bị lỗi TransactionRequiredException
    public void dropoutStudent(Long courseId, Long studentId) {
        boolean existed = studentEnrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId);
        if (!existed) {
            throw new RuntimeException("Enrollment not found");
        }
        studentEnrollmentRepository.deleteByStudentIdAndCourseId(studentId, courseId);
=======
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
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
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
