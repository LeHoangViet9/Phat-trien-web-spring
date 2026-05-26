package com.rikkei.session7.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CandidateCreateDTO {
//    fullName: Không được để trống, độ dài 5-50 ký tự.
//    email: Không được để trống, đúng định dạng email.
//            age: Số nguyên, tối thiểu 18 tuổi.
//            yearsOfExperience: Số nguyên, không âm (>= 0).
    @NotBlank(message = "Không được đê trông") @Length(min=5, max=50, message = "Độ dài 5-50 kí tự")
    private String fullName;
    @NotBlank(message = "Không được để trống")
    @Email
    private String email;
    @Min(18)
    private Integer age;
    @Min(0)
    private Integer yearsOfExperience;
    @NotBlank(message = "Không được để trống")
    private String address;
    @Size(max =200, message = "Không được quá 200 kí tự")
    private String bio;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^0\\d{9}$",message = "Số điện thoại không hợp lệ")
    private String phone;

}
