package com.learn.airline.service.impl;

import com.learn.airline.mapper.CityMapper;
import com.learn.airline.model.City;
import com.learn.airline.payload.request.CityRequest;
import com.learn.airline.payload.response.CityResponse;
import com.learn.airline.repository.CityRepository;
import com.learn.airline.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest cityRequest) throws Exception {
        if (cityRepository.existsByCityCode(cityRequest.getCityCode())) {
            throw new Exception("City with code " + cityRequest.getCityCode() + " already exists.");
        }

        City city = CityMapper.getCity(cityRequest);
        City savedCity = cityRepository.save(city);
        return CityMapper.getCityResponse(savedCity);
    }

    @Override
    public CityResponse getCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
        return CityMapper.getCityResponse(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        Page<City> cities = cityRepository.findAll(pageable);
        return cities.map(CityMapper::getCityResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        Page<City> cities = cityRepository.searchByKeyword(keyword, pageable);
        return cities.map(CityMapper::getCityResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        Page<City> cities = cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable);
        return cities.map(CityMapper::getCityResponse);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) {
       City cityRequested = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));

        if (cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(), id)) {
            throw new RuntimeException("City with code " + cityRequest.getCityCode() + " already exists.");
        }

        City updatedCity = CityMapper.updateEntity(cityRequested, cityRequest);
        City savedCity = cityRepository.save(updatedCity);
        return CityMapper.getCityResponse(savedCity);
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
        cityRepository.delete(city);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
