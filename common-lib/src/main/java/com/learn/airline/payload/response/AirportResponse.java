package com.learn.airline.payload.response;

import com.learn.airline.embeddable.Address;
import com.learn.airline.embeddable.GeoCode;

public class AirportResponse {

    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private String timeZone;
    private Address address;
    private CityResponse city;
    private GeoCode geoCode;
}
