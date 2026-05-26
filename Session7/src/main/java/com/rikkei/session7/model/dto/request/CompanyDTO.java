package com.rikkei.session7.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDTO {
    @NotBlank(message = "Company name cannot blank")
    private String name;
    @Size(min = 10,max = 13,message = "Chỉ được 10 đến 13 kí tự")
    private String taxCode;

}
