package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.web.model.AirlineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirlineMapperTest {
    public static final String TURKISH_AIRLINES_NAME = "Turkish Airlines";
    public static final long TURKISH_AIRLINES_ID = 1L;

    AirlineMapper airlineMapper;

    @BeforeEach
    void setUp() {
        airlineMapper = new AirlineMapperImpl();
    }

    @Test
    void airlineDtoToAirline() {
        //given
        AirlineDto airlineDto = AirlineDto.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        //when
        //then
        Airline airline = airlineMapper.airlineDtoToAirline(airlineDto);
        assertEquals(TURKISH_AIRLINES_ID, airline.getId());
        assertEquals(TURKISH_AIRLINES_NAME, airline.getAirlineName());
    }

    @Test
    void airlineToAirlineDto() {
        //given
        Airline airline = Airline.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        //when
        //then
        AirlineDto airlineDto = airlineMapper.airlineToAirlineDto(airline);
        assertEquals(TURKISH_AIRLINES_ID, airlineDto.getId());
        assertEquals(TURKISH_AIRLINES_NAME, airlineDto.getAirlineName());
    }
}