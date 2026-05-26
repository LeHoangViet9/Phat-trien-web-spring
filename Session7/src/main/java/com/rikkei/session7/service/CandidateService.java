package com.rikkei.session7.service;

import com.rikkei.session7.model.dto.request.CandidateCreateDTO;
import com.rikkei.session7.model.entity.Candidate;
import org.springframework.data.domain.Page;

public interface CandidateService {
    Page<Candidate> getAllCandidates(Integer page, Integer size);
    Candidate insertCandidate(CandidateCreateDTO candidate);
    Candidate updateCandidate(Long id, CandidateCreateDTO candidate);
}
