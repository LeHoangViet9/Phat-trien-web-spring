package com.session04.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id",nullable = false)
    private Long id;
    private String title;
    @Column(name = "course_status")
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    @ManyToOne
    @JoinColumn(name = "instructor_id",referencedColumnName = "id")
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<StudentEnrollment> studentEnrollments=new ArrayList<>();

}
