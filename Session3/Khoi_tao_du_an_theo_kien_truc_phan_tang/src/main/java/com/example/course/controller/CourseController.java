package com.example.course.controller;

import com.example.course.models.Course;
import com.example.course.payload.ApiResponse;
import com.example.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(){
        List<Course> courses=courseService.getCourses();
        ApiResponse<List<Course>> apiResponse = new ApiResponse<>(true,"Get all courses successfully",courses);
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id){
        Course course=courseService.getCourseById(id);
        ApiResponse<Course> apiResponse=new ApiResponse<>(true,"Get course successfully",course);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody Course course){
        try {
            Course createCou=courseService.addCourse(course);
            ApiResponse<Course> apiResponse=new ApiResponse<>(true,"Create course successfully",createCou);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<Course> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@RequestBody Course course, @PathVariable Long id){
       try {
           Course updateCou=courseService.updateCourse(id, course);
           ApiResponse<Course> apiResponse=new ApiResponse<>(true,"Update course successfully",updateCou);
           return ResponseEntity.ok(apiResponse);
       }catch (RuntimeException e){
           ApiResponse<Course> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
       }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteCourse(id);
            ApiResponse<String> apiResponse=new ApiResponse<>(true,"Delete course successfully",null);
            return ResponseEntity.ok(apiResponse);
        }catch (RuntimeException e){
            ApiResponse<String> apiResponse=new ApiResponse<>(false,e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
}
