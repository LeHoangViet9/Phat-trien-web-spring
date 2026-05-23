package com.rikkei.session6.service;

import com.rikkei.session6.model.dto.response.ZoneStatusticsResponse;

import java.util.List;

public interface IZoneService {
    List<ZoneStatusticsResponse> getZoneStatustics1();
    List<ZoneStatusticsResponse> getZoneStatustics2();
}
