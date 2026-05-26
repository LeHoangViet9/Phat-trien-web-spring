package com.rikkei.session7.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobCreateDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Min(value = 0,message = "Min salary phải lớn hơn 0")
    private Double minSalary;
    @Min(value = 0, message = "Max salary phải lớn hơn 0")
    private Double maxSalary;
    @Valid
    @NotNull(message = "Company cannot be null")
    private CompanyDTO company;
}
