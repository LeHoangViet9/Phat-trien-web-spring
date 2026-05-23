package com.session04.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "instructor_name",length = 100)
    private String name;
    @Column(name = "email",nullable = false,length = 100)
    private String email;
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> courses;
}
