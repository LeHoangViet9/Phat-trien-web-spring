package com.session04.repository;

import com.session04.model.entity.Course;
import com.session04.model.entity.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByIdAndStatus(Long courseId, CourseStatus status);
}
