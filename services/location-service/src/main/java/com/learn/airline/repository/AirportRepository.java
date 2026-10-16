package com.learn.airline.repository;

import com.learn.airline.model.Airport;
import com.learn.airline.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findbyIataCode(String iataCode);
    List<Airport> getByCityId(Long cityId);
}
