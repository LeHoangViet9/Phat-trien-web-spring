package com.rikkei.session6.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table( name = "parking_ticket", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"vehicle_id","zone_id"})
}
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_in_time",nullable = false)
    private LocalDateTime checkInTime;
    @Column(name = "check_out_time",nullable = false)
    private LocalDateTime checkOutTime;
    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;
}
