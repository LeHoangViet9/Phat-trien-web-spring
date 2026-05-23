package com.session04.repository;

import com.session04.model.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
=======
import org.springframework.stereotype.Repository;
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e

import java.util.List;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
<<<<<<< HEAD
    @Modifying
    @Transactional
=======
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
>>>>>>> c4b39f1be6a603d1182ab456bb21746cf5482a7e
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
    List<StudentEnrollment> findByCourseIdAndStudentStuNameContainingIgnoreCase(Long id,String name);

}
