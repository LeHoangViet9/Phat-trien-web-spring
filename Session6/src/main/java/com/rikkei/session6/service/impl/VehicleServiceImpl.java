package com.rikkei.session6.service.impl;

import com.rikkei.session6.model.dto.request.VehicleCreateRequest;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.VehicleResponse;
import com.rikkei.session6.model.entity.Vehicle;
import com.rikkei.session6.repository.VehicleRepository;
import com.rikkei.session6.service.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final JsonMapper.Builder builder;

    @Override
    public VehicleResponse create(VehicleCreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .type(request.getType()).build();
        vehicle=vehicleRepository.save(vehicle);
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .licensePlate(vehicle.getLicensePlate())
                .color(vehicle.getColor())
                .type(vehicle.getType())
                .build();
    }

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        if (page < 0) {
            page = 0;
        }
        Sort sort= Sort.unsorted();
        if(sortBy!=null&&!sortBy.isBlank()&&direction!=null&&!direction.isBlank()&&!direction.isBlank()){
            sort=direction.equalsIgnoreCase("DESC")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<VehicleResponse> vehiclePage=vehicleRepository.findAllByKeyword(keyword, pageable);

        return PageResponse.<VehicleResponse>builder()
                .items(vehiclePage.getContent())
                .page(vehiclePage.getNumber())
                .size(vehiclePage.getSize())
                .totalItems(vehiclePage.getTotalElements())
                .totalPages(vehiclePage.getTotalPages())
                .isLast(vehiclePage.isLast()).build();
    }
}
