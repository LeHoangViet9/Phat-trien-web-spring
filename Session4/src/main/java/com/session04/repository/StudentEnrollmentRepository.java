package com.session04.repository;

import com.session04.model.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    @Modifying
    @Transactional
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
    List<StudentEnrollment> findByCourseIdAndStudentStuNameContainingIgnoreCase(Long id,String name);

}
