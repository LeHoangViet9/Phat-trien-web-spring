package com.session04.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stu_name",length = 100,nullable = false)
    private String stuName;
    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;
    @OneToMany(mappedBy = "student")
    private List<StudentEnrollment> studentEnrollments;
}
