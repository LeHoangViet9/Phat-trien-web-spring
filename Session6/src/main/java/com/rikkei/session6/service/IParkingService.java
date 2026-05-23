package com.rikkei.session6.service;

import com.rikkei.session6.model.dto.request.TicketRequest;
import com.rikkei.session6.model.dto.response.PageResponse;
import com.rikkei.session6.model.dto.response.TicketResponse;
import com.rikkei.session6.model.dto.response.TicketSumaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IParkingService {
    TicketResponse checkIn(TicketRequest request);
    TicketResponse checkOut(Long vehicleId);
    List<TicketSumaryResponse> findTodayTickets();
    PageResponse<TicketSumaryResponse> findHistoryTickets(String licensePlate, LocalDateTime fromDate, LocalDateTime toDate, Integer page, Integer size);
}
