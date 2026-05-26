package com.rikkei.session7.controller;

import com.rikkei.session7.exception.CustomerException;
import com.rikkei.session7.model.dto.request.JobCreateDTO;
import com.rikkei.session7.model.dto.response.ApiDataResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")

public class JobController {
    @PostMapping
    public ResponseEntity<ApiDataResponse<JobCreateDTO>>  createJob(@Valid @RequestBody JobCreateDTO jobCreateDTO) {
        if(jobCreateDTO.getMinSalary()>jobCreateDTO.getMaxSalary()) {
            throw new CustomerException("Lương tối thiểu không được lớn hơn lương tốt đa");
        }
        return new  ResponseEntity<>(new ApiDataResponse<JobCreateDTO>(
                true,
                "Thêm job thành công",
                jobCreateDTO,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
}
