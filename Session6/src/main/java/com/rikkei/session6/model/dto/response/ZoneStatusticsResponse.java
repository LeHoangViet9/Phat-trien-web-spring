package com.rikkei.session6.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ZoneStatusticsResponse {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer occupiedSlots;
    private Integer availableSlots;
}
