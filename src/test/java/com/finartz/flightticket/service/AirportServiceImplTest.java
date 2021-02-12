package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.repository.AirportRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.AirportMapper;
import com.finartz.flightticket.web.model.AirportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class AirportServiceImplTest {
    public static final String ISTANBUL_AIRPORT_NAME = "Istanbul Airport";
    public static final long ISTANBUL_AIRPORT_ID = 1L;
    public static final String HEATHROW_AIRPORT_NAME = " Heathrow Airport";
    public static final long HEATHROW_AIRPORT_ID = 2L;
    @Mock
    AirportRepository airportRepository;

    AirportMapper airportMapper = AirportMapper.INSTANCE;

    AirportService airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airportService = new AirportServiceImpl(airportRepository, airportMapper);
    }

    @Test
    void findAirport(){
        //given
        Airport airport = Airport.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        AirportDto airportDto = AirportDto.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        //when
        given(airportRepository.findById(airport.getId())).willReturn(Optional.of(airport));

        //then
        AirportDto result = airportService.getAirportById(airport.getId());
        assertEquals(airportDto, result);
    }

    @Test
    void findAirports(){
        //given
        List<Airport> airports = new ArrayList<>();
        Airport ataturkAirport = Airport.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();
        airports.add(ataturkAirport);

        Airport heathrowAirport = Airport.builder()
                .id(HEATHROW_AIRPORT_ID)
                .airportName(HEATHROW_AIRPORT_NAME)
                .build();
        airports.add(heathrowAirport);

        List<AirportDto> airportDtoList = new ArrayList<>();
        AirportDto ataturkAirportDto = AirportDto.builder()
                .id(ISTANBUL_AIRPORT_ID)
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();
        airportDtoList.add(ataturkAirportDto);

        AirportDto heathrowAirportDto = AirportDto.builder()
                .id(HEATHROW_AIRPORT_ID)
                .airportName(HEATHROW_AIRPORT_NAME)
                .build();
        airportDtoList.add(heathrowAirportDto);

        //when
        given(airportRepository.findAll()).willReturn(airports);

        //then
        List<AirportDto> result = airportService.getAllAirports();
        assertEquals(airportDtoList, result);
    }

    @Test
    void findAllAirport_shouldFail() {
        given(airportRepository.findById(ISTANBUL_AIRPORT_ID)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> airportService.getAirportById(ISTANBUL_AIRPORT_ID));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Airport could not be found.", throwable.getMessage());
    }

    @Test
    void saveAirport(){
        //given
        Airport airport = Airport.builder()
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        AirportDto airportDto = AirportDto.builder()
                .airportName(ISTANBUL_AIRPORT_NAME)
                .build();

        //when
        given(airportRepository.save(airport)).willReturn(airport);

        //then
        AirportDto result = airportService.saveAirport(airportDto);
        verify(airportRepository).save(ArgumentMatchers.argThat(airport1 -> {
            assertEquals(airport, airport1);
            return true;
        }));

        assertEquals(airportDto, result);
    }
}