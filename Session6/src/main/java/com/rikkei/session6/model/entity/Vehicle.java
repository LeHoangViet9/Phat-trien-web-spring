package com.rikkei.session6.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate", nullable = false,length = 20)
    private String licensePlate;
    @Column(nullable = false)
    private String color;
    private VehicleType type;
    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTicket;
}
