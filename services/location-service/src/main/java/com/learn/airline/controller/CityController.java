package com.learn.airline.controller;

import com.learn.airline.payload.request.CityRequest;
import com.learn.airline.payload.response.ApiResponse;
import com.learn.airline.payload.response.CityResponse;
import com.learn.airline.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    // Create
    @PostMapping
    public ResponseEntity<CityResponse> createCity(
            @Valid @RequestBody CityRequest cityRequest
    ) throws Exception {
        CityResponse createdCity = cityService.createCity(cityRequest);
        return ResponseEntity.ok(createdCity);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        CityResponse cityResponse = cityService.getCityById(id);
        return ResponseEntity.ok(cityResponse);
    }

    // Read
    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> cityResponses = cityService.getAllCities(pageable);
        return ResponseEntity.ok(cityResponses);
    }

    // Search
    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CityResponse> cityResponses = cityService.searchCities(keyword, pageable);
        return ResponseEntity.ok(cityResponses);
    }

    // Filter
    @GetMapping("/Country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CityResponse> cityResponses = cityService.getCitiesByCountryCode(countryCode, pageable);
        return ResponseEntity.ok(cityResponses);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable Long id,
            @Valid @RequestBody CityRequest cityRequest
    ) throws Exception {
        CityResponse updatedCity = cityService.updateCity(id, cityRequest);
        return ResponseEntity.ok(updatedCity);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("City deleted successfully"));
    }

    // Validation
    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> cityExists(@PathVariable String cityCode) {
        boolean exists = cityService.cityExists(cityCode);
        return ResponseEntity.ok(exists);
    }

}
