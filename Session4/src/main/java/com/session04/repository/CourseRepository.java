package com.session04.repository;

import com.session04.model.entity.Course;
import com.session04.model.entity.CourseStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByIdAndStatus(Long courseId, CourseStatus status);
//    @Query("Select c From Course c where c.status like :status")
    @Query("Select new com.session04.model.dto.response.CourseResponseV2(c.id,c.title,c.status) From Course ourse where c.status = :status" )
    Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);

    @Query("Select c From Course c Where (:status IS NULL OR c.status =:status) AND (:keyword IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Course>searchCourse(CourseStatus status, String keyword, Pageable pageable);

}
