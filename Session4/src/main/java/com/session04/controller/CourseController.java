package com.session04.controller;

import com.session04.model.dto.request.CourseCreateRequest;
import com.session04.model.dto.response.ApiResponse;
import com.session04.model.dto.response.CourseResponse;
import com.session04.model.dto.response.CourseResponseV2;
import com.session04.model.dto.response.PageResponse;
import com.session04.model.entity.CourseStatus;
import com.session04.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public ResponseEntity<CourseResponseV2> createCourse(
            @RequestBody CourseCreateRequest request
    ) {

        return ResponseEntity.ok(
                courseService.createCourse(request)
        );
    }
// Session 4
//    @GetMapping
//    public ResponseEntity<List<CourseResponse>> getCourses() {
//
//        return ResponseEntity.ok(
//                courseService.findAll()
//        );
//    }

    // Session 5 dùng Pageable
//    @GetMapping
//    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCourse(@RequestParam(name="page",defaultValue = "0")Integer page,
//                                                                          @RequestParam(name="size", defaultValue = "10")Integer size,
//                                                                          @RequestParam(name="sortBy",required = false)String sortBy,
//                                                                          @RequestParam(name="direction",defaultValue = "DESC")Sort.Direction direction
//                                                                         ){
//        PageResponse<CourseResponse> coursePage = courseService.findAllByPage(page, size, sortBy, direction);
//
//        ApiResponse<PageResponse<CourseResponse>> apiResponse = new ApiResponse<>(
//                true,
//                "Lấy danh sách thành công",
//                coursePage,
//                HttpStatus.OK
//        );
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getCourses(
            @RequestParam(required = false)CourseStatus status,
            @RequestParam(required = false)String keyword,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction
            ){

        return ResponseEntity.ok(new  ApiResponse<>(
                true,
                "Lấy danh sách khóa học thành công",
                courseService.getAllCourse(
                        status,keyword,page,size,sortBy,direction
                ), HttpStatus.OK
        ));
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponse> getCourse(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                courseService.findById(id)
        );
    }
}
