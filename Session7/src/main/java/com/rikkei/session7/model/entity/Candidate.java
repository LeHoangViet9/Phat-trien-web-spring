package com.rikkei.session7.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Candidate {
//    Id : mã ứng viên ,số nguyên , tự tăng .
//            fullName: tên ứng viên ,kiểu dữ liệu chuỗi .
//    email: email , kiểu dữ liệu chuỗi .
//    Age: tuổi , số nguyên .
//    yearsOfExperience: năm kinh nghiệm , số nguyên.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;
    @Column(length = 100,nullable = false,unique = true)
    private String email;
    private Integer age;
    @Column(name = "year_of_experience",nullable = false)
    private Integer yearOfExperience;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String bio;
    @Column(nullable = true)
    private String phone;
}
