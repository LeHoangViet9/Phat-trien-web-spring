package com.example.course.controller;

import com.example.course.models.DTO.EnrollCourseRequest;
import com.example.course.models.DTO.EnrollmentDetail;
import com.example.course.models.Enrollment;
import com.example.course.payload.ApiResponse;
import com.example.course.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments(){
        List<Enrollment> enrollments = enrollmentService.getEnrollments();
        ApiResponse<List<Enrollment>> apiResponse = new ApiResponse<>(true,"Get all enrollments",enrollments);
        return ResponseEntity.ok(apiResponse);


    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Long id){
        try {
            Enrollment enrollment=enrollmentService.getEnrollment(id);
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(true,"Get enrollment by id",enrollment);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody Enrollment enrollment) {
        try {
            Enrollment enrollment1 = enrollmentService.addEnrollment(enrollment);
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(true, "Add enrollment successfully", enrollment1);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment){
        try {
            Enrollment updatedEnrollment=enrollmentService.updateEnrollment(id, enrollment);
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(true,"Update enrollment by id",updatedEnrollment);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(@PathVariable Long id){
        try {
            enrollmentService.deleteEnrollment(id);
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(true,"Delete enrollment by id",null);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Enrollment> apiResponse = new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> enrollCourse(
            @RequestBody EnrollCourseRequest request){

        try {

            EnrollmentDetail detail =
                    enrollmentService.getEnrollmentDetail(request);

            ApiResponse<EnrollmentDetail> response =
                    new ApiResponse<>(
                            true,
                            "Enroll course successfully",
                            detail
                    );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);

        } catch (RuntimeException e){

            ApiResponse<EnrollmentDetail> response =
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }
}
