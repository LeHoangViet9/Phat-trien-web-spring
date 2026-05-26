package com.rikkei.session7.controller;

import com.rikkei.session7.model.dto.request.CandidateCreateDTO;
import com.rikkei.session7.model.dto.response.ApiDataResponse;
import com.rikkei.session7.model.entity.Candidate;
import com.rikkei.session7.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Candidate>> addCandidate(@Valid @RequestBody CandidateCreateDTO candidate) {
            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Thêm mới "+candidate.getFullName()+" thành công",
                    candidateService.insertCandidate(candidate),
                    null,
                    HttpStatus.CREATED

            ), HttpStatus.CREATED);
    }

    @PutMapping("/updates/{id}")
    public ResponseEntity<ApiDataResponse<Candidate>> updateCandidate(@Valid @RequestParam(name = "id") Long id,
                                                                      @RequestBody CandidateCreateDTO candidate) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhập sản phẩm "+id+" thành công",
                candidateService.updateCandidate(id,candidate),
                null,
                HttpStatus.OK
        ),HttpStatus.OK);
    }
}
