package com.rikkei.session6.repository;

import com.rikkei.session6.model.dto.response.ZoneStatusticsResponse;
import com.rikkei.session6.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long> {
    @Query("Select new com.rikkei.session6.model.dto.response.ZoneStatusticsResponse(z.id,z.name,z.capacity,z.occupiedSpots,(z.capacity-z.occupiedSpots)) " +
            "From Zone z")
    List<ZoneStatusticsResponse> getZoneStatustics();

}
