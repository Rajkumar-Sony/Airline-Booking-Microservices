package com.learn.airline.payload.request;

import jakarta.validation.Validation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CityRequestValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void acceptsThreeUppercaseLettersAsCityCode() {
        CityRequest request = CityRequest.builder().cityCode("ABC").build();

        assertThat(validator.validateProperty(request, "cityCode")).isEmpty();
    }

    @Test
    void rejectsCityCodesThatAreNotThreeUppercaseLetters() {
        assertThat(validateCityCode("AB")).isNotEmpty();
        assertThat(validateCityCode("abcd")).isNotEmpty();
        assertThat(validateCityCode(" A1 ")).isNotEmpty();
    }

    @Test
    void rejectsNullOrBlankCityCodes() {
        assertThat(validateCityCode(null)).isNotEmpty();
        assertThat(validateCityCode("   ")).isNotEmpty();
    }

    @Test
    void acceptsValidCountryAndRegionCodes() {
        CityRequest request = CityRequest.builder()
                .countryCode("US")
                .regionCode("US-CA")
                .build();

        assertThat(validator.validateProperty(request, "countryCode")).isEmpty();
        assertThat(validator.validateProperty(request, "regionCode")).isEmpty();
    }

    @Test
    void rejectsInvalidCountryAndRegionCodes() {
        assertThat(validateCountryCode("USA")).isNotEmpty();
        assertThat(validateCountryCode("uS")).isNotEmpty();
        assertThat(validateRegionCode("CA")).isNotEmpty();
        assertThat(validateRegionCode("US-CALI")).isNotEmpty();
    }

    private Set<ConstraintViolation<CityRequest>> validateCountryCode(String countryCode) {
        CityRequest request = CityRequest.builder().countryCode(countryCode).build();
        return validator.validateProperty(request, "countryCode");
    }

    private Set<ConstraintViolation<CityRequest>> validateRegionCode(String regionCode) {
        CityRequest request = CityRequest.builder().regionCode(regionCode).build();
        return validator.validateProperty(request, "regionCode");
    }

    private Set<ConstraintViolation<CityRequest>> validateCityCode(String cityCode) {
        CityRequest request = CityRequest.builder().cityCode(cityCode).build();
        return validator.validateProperty(request, "cityCode");
    }
}
