package com.rikkei.session6.controller;

import com.rikkei.session6.model.dto.response.ApiResponse;
import com.rikkei.session6.model.dto.response.ZoneStatusticsResponse;
import com.rikkei.session6.service.IZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zones")
@RequiredArgsConstructor
public class ZoneController {
    private final IZoneService zoneService;
    @GetMapping("/v1/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatusticsResponse>>> getStatsV1() {

        ApiResponse<List<ZoneStatusticsResponse>> response =
                new ApiResponse<>(
                        true,
                        "Get zone statistics v1 successfully",
                        zoneService.getZoneStatustics1()
                );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v2/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatusticsResponse>>> getStatsV2() {

        ApiResponse<List<ZoneStatusticsResponse>> response =
                new ApiResponse<>(
                        true,
                        "Get zone statistics v2 successfully",
                        zoneService.getZoneStatustics2()
                );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
