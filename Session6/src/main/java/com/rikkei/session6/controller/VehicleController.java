package com.rikkei.session6.controller;

import com.rikkei.session6.model.dto.request.VehicleCreateRequest;
import com.rikkei.session6.model.dto.response.ApiResponse;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.VehicleResponse;
import com.rikkei.session6.model.entity.Vehicle;
import com.rikkei.session6.service.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final IVehicleService vehicleService;
    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> create(@RequestBody VehicleCreateRequest vehicle) {
       ApiResponse<VehicleResponse> response = new ApiResponse<>(
               true,
               "Add vehicle successfully",
               vehicleService.create(vehicle)
       );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>>getPagedVehicles(@RequestParam(defaultValue = "0") Integer page,
                                                                         @RequestParam(defaultValue = "10") Integer size,
                                                                         @RequestParam(required = false) String sortBy,
                                                                         @RequestParam(required = false) String direction,
                                                                         @RequestParam(required = false)String keyword)
    {
        ApiResponse<PageResponse<VehicleResponse>> response = new ApiResponse<>(
                true,
                "Get vehicles successfully",
                vehicleService.getPagedVehicles(page,size,sortBy,direction,keyword)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
