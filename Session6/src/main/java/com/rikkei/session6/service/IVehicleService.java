package com.rikkei.session6.service;

import com.rikkei.session6.model.dto.request.VehicleCreateRequest;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.VehicleResponse;
import org.springframework.data.domain.Pageable;

public interface IVehicleService {
    VehicleResponse create(VehicleCreateRequest request);
    PageResponse<VehicleResponse> getPagedVehicles(int page, int size,String sortBy, String direction,String keyword);
}
