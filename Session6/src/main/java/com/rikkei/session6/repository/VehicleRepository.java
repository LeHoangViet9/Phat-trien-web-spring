package com.rikkei.session6.repository;

import com.rikkei.session6.model.dto.response.VehicleResponse;
import com.rikkei.session6.model.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("""
    SELECT new com.rikkei.session6.model.dto.response.VehicleResponse(
        v.id,
        v.licensePlate,
        v.color,
        v.type
    )
    FROM Vehicle v
    WHERE
        (:keyword IS NULL
        OR LOWER(v.licensePlate) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(v.color) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
    Page<VehicleResponse> findAllByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );

    Vehicle existsVehicleById(Long id);
}
