package com.rikkei.session6.model.dto.response;

import com.rikkei.session6.model.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType type;
}
