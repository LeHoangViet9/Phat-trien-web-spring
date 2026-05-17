package com.example.course.controller;

import com.example.course.models.DTO.IntructorDetail;
import com.example.course.models.Enrollment;
import com.example.course.models.Instructor;
import com.example.course.payload.ApiResponse;
import com.example.course.service.InstructorService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private InstructorService instructorService;
    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllEnrollments(){
        List<Instructor> instructors=instructorService.getInstructors();
        ApiResponse<List<Instructor>> apiResponse=new ApiResponse<>(true,"Get all instructors",instructors);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getEnrollmentById(@PathVariable Long id){
        try {
            Instructor instructor=instructorService.getInstructor(id);
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(true,"Get instructor",instructor);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createEnrollment(@RequestBody Instructor instructor){
        try {
            Instructor instructor1=instructorService.addInstructor(instructor);
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(true,"Add instructor",instructor1);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateEnrollment(@PathVariable Long id, @RequestBody Instructor instructor){
        try {
            Instructor instructor1=instructorService.updateInstructor(id, instructor);
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(true,"Update",instructor1);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteEnrollment(@PathVariable Long id){
        try {
            instructorService.deleteInstructor(id);
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(true,"Delete",null);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Instructor> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @GetMapping("/details")
    public ResponseEntity<ApiResponse<List<IntructorDetail>>> getInstructorDetails() {
        List<IntructorDetail> instructors=instructorService.getInstructorDetail();
        ApiResponse<List<IntructorDetail>> apiResponse=new ApiResponse<>(true,"Get instructor details",instructors);
        return ResponseEntity.ok(apiResponse);
    }
}
