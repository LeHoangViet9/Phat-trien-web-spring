package com.example.course.repository;

import com.example.course.models.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private List<Enrollment> enrollments;

    public EnrollmentRepository() {
        enrollments = new ArrayList<>();

        enrollments.add(new Enrollment(1L, "Le Hoang Viet", 1L));
        enrollments.add(new Enrollment(2L, "Nguyen Van C", 2L));
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollments.stream().filter(e -> e.getId() == id).findFirst();

    }
    public Enrollment addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }
    public Enrollment updateEnrollment(Long id,Enrollment enrollment) {
        Enrollment existingEnrollment = getEnrollmentById(id).orElseThrow(()-> new RuntimeException("enrollment not found"));
        existingEnrollment.setStudentName(enrollment.getStudentName());
        existingEnrollment.setCourseId(enrollment.getCourseId());
        return existingEnrollment;
    }
    public void deleteEnrollment(Long id) {
        for (int i = 0;  i < enrollments.size() ; i ++) {
            if(enrollments.get(i).getId() == id){
                enrollments.remove(i);
            }
        }
    }
    public boolean existByCourseId(Long couId){
            return enrollments.stream().anyMatch(e -> e.getCourseId().equals(couId));
    }
}
