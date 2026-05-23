package com.rikkei.session6.controller;

import com.rikkei.session6.model.dto.request.TicketRequest;
import com.rikkei.session6.model.dto.response.ApiResponse;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.TicketResponse;
import com.rikkei.session6.model.dto.response.TicketSumaryResponse;
import com.rikkei.session6.service.IParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apt/v1/tickets")
public class ParkingTicketController {
    private final IParkingService parkingService;
    @PostMapping
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest ticketRequest){
        ApiResponse<TicketResponse> response=new ApiResponse<>(
                true,
                "Check in successfully",
                parkingService.checkIn(ticketRequest)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable Long vehicleId){
        ApiResponse<TicketResponse> response=new ApiResponse<>(
                true,
                "Check out successfully",
                parkingService.checkOut(vehicleId)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<PageResponse<TicketSumaryResponse>>> findHistoryTickets(@RequestParam(required = false) String licensePlate,
                                                                                                       @RequestParam(required = false) @DateTimeFormat(iso =DateTimeFormat.ISO.DATE_TIME)LocalDateTime fromDate,
                                                                                                       @RequestParam(required = false) @DateTimeFormat(iso =DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                                                                       @RequestParam(defaultValue = "0") Integer page,
                                                                                                       @RequestParam(defaultValue = "10")  Integer size){
        ApiResponse<PageResponse<TicketSumaryResponse>> response=new ApiResponse<>(
                true,
                "Find ticket successfully",
                parkingService.findHistoryTickets(licensePlate,fromDate,toDate,page,size)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
