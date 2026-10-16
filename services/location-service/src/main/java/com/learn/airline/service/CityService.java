package com.learn.airline.service;

import com.learn.airline.payload.request.CityRequest;
import com.learn.airline.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    // Create
    CityResponse createCity(CityRequest cityRequest) throws Exception;

    // Read
    CityResponse getCityById(Long id);
    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCities(String keyword, Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

    // Update
    CityResponse updateCity(Long id, CityRequest cityRequest) throws Exception;

    // Delete
    void deleteCity(Long id);

    // Validation
    boolean cityExists(String cityCode);
}
