package com.rikkei.session6.model.dto.request;
import com.rikkei.session6.model.entity.VehicleType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleCreateRequest {
    String licensePlate;
    String color;
    VehicleType type;
}
