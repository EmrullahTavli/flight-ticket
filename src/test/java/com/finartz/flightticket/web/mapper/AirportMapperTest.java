package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.web.model.AirportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirportMapperTest {
    public static final String ISTANBUL_AIRPORT_NAME = "Istanbul Airport";
    public static final long ISTANBUL_AIRPORT_ID = 1L;
    AirportMapper airportMapper;

    @BeforeEach
    void setUp() {
        airportMapper = new AirportMapperImpl();
    }

    @Test
    void airportDtoToAirport() {
        //given
        AirportDto airportDto = AirportDto.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        //when
        //then
        Airport airport = airportMapper.airportDtoToAirport(airportDto);
        assertEquals(ISTANBUL_AIRPORT_ID, airport.getId());
        assertEquals(ISTANBUL_AIRPORT_NAME, airport.getAirportName());
    }

    @Test
    void airportToAirportDto() {
        //given
        Airport airport = Airport.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        //when
        //then
        AirportDto airportDto = airportMapper.airportToAirportDto(airport);
        assertEquals(ISTANBUL_AIRPORT_ID, airportDto.getId());
        assertEquals(ISTANBUL_AIRPORT_NAME, airportDto.getAirportName());
    }
}