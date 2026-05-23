package com.rikkei.session6.service.impl;

import com.rikkei.session6.model.dto.request.TicketRequest;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.TicketResponse;
import com.rikkei.session6.model.dto.response.TicketSumaryResponse;
import com.rikkei.session6.model.entity.ParkingTicket;
import com.rikkei.session6.model.entity.Vehicle;
import com.rikkei.session6.model.entity.Zone;
import com.rikkei.session6.repository.ParkingRepository;
import com.rikkei.session6.repository.VehicleRepository;
import com.rikkei.session6.repository.ZoneRepository;
import com.rikkei.session6.service.IParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingServiceImpl implements IParkingService {
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    @Override
    public TicketResponse checkIn(TicketRequest request) {
        Vehicle vehicle=vehicleRepository.findById(request.getVehicleId()).orElseThrow(()->new RuntimeException("Vehicle not found"));
        Zone zone=zoneRepository.findById(request.getZoneId()).orElseThrow(()->new RuntimeException("Zone not found"));
        if(zone.getOccupiedSpots()>=zone.getCapacity()){
            throw new  RuntimeException("Zone is full");
        }
        ParkingTicket parkingTicket=ParkingTicket.builder()
                .vehicle(vehicle)
                .zone(zone)
                .checkInTime(LocalDateTime.now()).build();
        zone.setOccupiedSpots(zone.getOccupiedSpots()+1);
        zoneRepository.save(zone);
        parkingRepository.save(parkingTicket);
        return TicketResponse.builder()
                .id(parkingTicket.getId())
                .licensePlate(vehicle.getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(parkingTicket.getCheckInTime())
                .checkOutTime(parkingTicket.getCheckOutTime()).build();

    }

    @Override
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket parkingTicket=parkingRepository.findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId).orElseThrow(()->new RuntimeException("Vehicle not found"));
        parkingTicket.setCheckOutTime(LocalDateTime.now());
        Zone zone=parkingTicket.getZone();
        zone.setOccupiedSpots(zone.getOccupiedSpots()-1);
        zoneRepository.save(zone);
        parkingRepository.save(parkingTicket);
        return TicketResponse.builder()
                .id(parkingTicket.getId())
                .licensePlate(parkingTicket.getVehicle().getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(parkingTicket.getCheckInTime())
                .checkOutTime(parkingTicket.getCheckOutTime()).build();
    }

    @Override
    public List<TicketSumaryResponse> findTodayTickets() {
        LocalDateTime start= LocalDate.now().atStartOfDay();
        LocalDateTime end= LocalDate.now().atTime(LocalTime.MAX);

        return parkingRepository.findTodayTickets(start,end);
    }

    @Override
    public PageResponse<TicketSumaryResponse> findHistoryTickets(String licensePlate, LocalDateTime fromDate, LocalDateTime toDate, Integer page, Integer size) {
        if(page<0){
            page=0;
        }
        Sort sort = Sort.by(Sort.Direction.DESC,"checkInTime");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<TicketSumaryResponse> ticketPage=parkingRepository.findTicketHistory(licensePlate,fromDate,toDate,pageable);
        return new PageResponse<>(ticketPage.getContent(),
                ticketPage.getNumber(),
                ticketPage.getSize(),
                ticketPage.getTotalElements(),
                ticketPage.getTotalPages(),
                ticketPage.isLast());
    }
}
