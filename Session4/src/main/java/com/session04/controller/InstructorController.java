package com.session04.controller;

import com.session04.model.dto.request.InstructorCreateRequest;
import com.session04.model.entity.Instructor;
import com.session04.repository.InstructorRepository;
import com.session04.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    @PostMapping
    public Instructor createInstructor(@RequestBody InstructorCreateRequest request){
        return instructorService.createInstructor(request);
    }

    @GetMapping
    public List<Instructor> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("{id}")
    public Instructor findInstructorById(@PathVariable Long id){
        return instructorService.findStudentById(id);
    }
}
