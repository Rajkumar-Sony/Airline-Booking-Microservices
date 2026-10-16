package com.learn.airline.service.impl;

import com.learn.airline.payload.request.AirportRequest;
import com.learn.airline.payload.response.AirportResponse;
import com.learn.airline.service.AirportService;

import java.util.List;

public class AirportServiceImpl implements AirportService {

    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        return null;
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        return null;
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return List.of();
    }

    @Override
    public AirportResponse getAirportByCityId(Long cityId) {
        return null;
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        return null;
    }

    @Override
    public void deleteAirport(Long id) {

    }
}
