package com.learn.airline.service;


import com.learn.airline.payload.request.AirportRequest;
import com.learn.airline.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {

    // Create
    AirportResponse createAirport(AirportRequest airportRequest);

    // Read
    AirportResponse getAirportById(Long id);
    List<AirportResponse> getAllAirports();
    AirportResponse getAirportByCityId(Long cityId);

    // Update
    AirportResponse updateAirport(Long id, AirportRequest airportRequest);

    // Delete
    void deleteAirport(Long id);
}
