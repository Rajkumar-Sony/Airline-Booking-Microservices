package com.learn.airline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.airline.embeddable.Address;
import com.learn.airline.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String airportName;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZoneId;

    @ManyToOne
    @JsonIgnore
    private City city;
}
