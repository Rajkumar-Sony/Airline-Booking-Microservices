package com.learn.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "City code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "City code must contain exactly 3 uppercase letters")
    private String cityCode;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must contain exactly 2 uppercase letters")
    private String countryCode;

    @NotBlank(message = "Country name is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String countryName;

    @NotBlank(message = "Region code is required")
    @Pattern(regexp = "^[A-Z]{2}-[A-Z0-9]{1,3}$", message = "Region code must be in the format 'XX-YYY' where 'XX' is the country code and 'YYY' is the region code")
    private String regionCode;

    @Size(max = 10, message = "Time zone offset must not exceed 10 characters")
    private String timeZoneOffset;
}
