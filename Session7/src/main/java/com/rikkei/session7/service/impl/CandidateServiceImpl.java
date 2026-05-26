package com.rikkei.session7.service.impl;

import com.rikkei.session7.exception.CandidateNotFount;
import com.rikkei.session7.model.dto.request.CandidateCreateDTO;
import com.rikkei.session7.model.entity.Candidate;
import com.rikkei.session7.repository.CandidateRepository;
import com.rikkei.session7.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    @Override
    public Page<Candidate> getAllCandidates(Integer page, Integer size) {
        return candidateRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Candidate insertCandidate(CandidateCreateDTO candidate) {
        Candidate cadidate=Candidate.builder()
                .fullName(candidate.getFullName())
                .email(candidate.getEmail())
                .age(candidate.getAge())
                .yearOfExperience(candidate.getYearsOfExperience())
                .address(candidate.getAddress())
                .bio(candidate.getBio())
                .phone(candidate.getPhone())
                .build();
        return candidateRepository.save(cadidate);
    }


    @Override
    public Candidate updateCandidate(Long id, CandidateCreateDTO candidate) {
        candidateRepository.findById(id).orElseThrow(()-> new CandidateNotFount("Candidate not found"));
        Candidate cadidate=Candidate.builder()
                .id(id)
                .fullName(candidate.getFullName())
                .email(candidate.getEmail())
                .age(candidate.getAge())
                .yearOfExperience(candidate.getYearsOfExperience())
                .address(candidate.getAddress())
                .bio(candidate.getBio())
                .phone(candidate.getPhone())
                .build();
        return candidateRepository.save(cadidate);
    }
}
