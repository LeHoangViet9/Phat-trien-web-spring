package com.rikkei.session6.service.impl;

import com.rikkei.session6.model.dto.response.ZoneStatusticsResponse;
import com.rikkei.session6.repository.ZoneRepository;
import com.rikkei.session6.service.IZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements IZoneService {
    private final ZoneRepository zoneRepository;

    @Override
    public List<ZoneStatusticsResponse> getZoneStatustics1() {
        List<ZoneStatusticsResponse> zoneStatusticsResponse = zoneRepository.findAll()
                .stream().map(zone -> ZoneStatusticsResponse.builder()
                        .id(zone.getId())
                        .name(zone.getName())
                        .capacity(zone.getCapacity())
                        .occupiedSlots(zone.getOccupiedSpots())
                        .availableSlots(zone.getCapacity()- zone.getOccupiedSpots()).build()).toList();
        return zoneStatusticsResponse;

    }

    @Override
    public List<ZoneStatusticsResponse> getZoneStatustics2() {
        List<ZoneStatusticsResponse> zoneStatustics = zoneRepository.getZoneStatustics();
        return zoneStatustics;
    }
}
