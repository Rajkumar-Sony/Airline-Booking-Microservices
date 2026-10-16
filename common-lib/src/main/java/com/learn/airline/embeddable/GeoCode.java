package com.learn.airline.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoCode {

    private Double latitude;
    private Double longitude;
}