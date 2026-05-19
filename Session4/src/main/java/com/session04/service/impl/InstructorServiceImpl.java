package com.session04.service.impl;

import com.session04.model.dto.request.InstructorCreateRequest;
import com.session04.model.entity.Instructor;
import com.session04.repository.InstructorRepository;
import com.session04.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    @Override
    public Instructor findStudentById(Long id) {
        return instructorRepository.findById(id).orElseThrow(()-> new RuntimeException("Can not found"));
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor createInstructor(InstructorCreateRequest request) {
        Instructor instructor = Instructor.builder().name(request.getName()).email(request.getEmail()).build();
        return instructorRepository.save(instructor);
    }
}
