package com.rikkei.session6.repository;

import com.rikkei.session6.model.dto.response.TicketResponse;
import com.rikkei.session6.model.dto.response.TicketSumaryResponse;
import com.rikkei.session6.model.entity.ParkingTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingTicket,Long> {
    Optional<ParkingTicket> findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("select new com.rikkei.session6.model.dto.response.TicketResponse(pt.id,v.licensePlate,z.name,pt.checkInTime,pt.checkOutTime) " +
            "From ParkingTicket pt Join pt.vehicle v Join pt.zone z " +
            "Where pt.checkInTime BETWEEN :start AND :end " +
            "ORDER By pt.checkInTime DESC")
    List<TicketSumaryResponse> findTodayTickets(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("""
        SELECT new com.rikkei.session6.model.dto.response.TicketSumaryResponse(
            pt.id,
            v.licensePlate,
            z.name,
            pt.checkInTime,
            pt.checkOutTime
        )
        FROM ParkingTicket pt
        JOIN pt.vehicle v
        JOIN pt.zone z
        WHERE
            (:licensePlate IS NULL OR v.licensePlate = :licensePlate)
        AND
            (:fromDate IS NULL OR pt.checkInTime >= :fromDate)
        AND
            (:toDate IS NULL OR pt.checkOutTime <= :toDate)
        """)
    Page<TicketSumaryResponse> findTicketHistory(
            @Param("licensePlate") String licensePlate,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );

}
