package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.repository.AirlineRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.AirlineMapper;
import com.finartz.flightticket.web.model.AirlineDto;
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

class AirlineServiceImplTest {
    public static final String TURKISH_AIRLINES_NAME = "Turkish Airlines";
    public static final long TURKISH_AIRLINES_ID = 1L;
    public static final String PEGASUS_AIRLINES_NAME = "Pegasus Airlines";
    public static final long PEGASUS_AIRLINES_ID = 2L;
    @Mock
    AirlineRepository airlineRepository;

    AirlineMapper airlineMapper = AirlineMapper.INSTANCE;

    AirlineService airlineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airlineService = new AirlineServiceImpl(airlineRepository, airlineMapper);
    }

    @Test
    void findAirline(){
        //given
        Airline airline = Airline.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        AirlineDto airlineDto = AirlineDto.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        //when
        given(airlineRepository.findById(airline.getId())).willReturn(Optional.of(airline));

        //then
        AirlineDto result = airlineService.getAirlineById(airlineDto.getId());
        assertEquals(airlineDto, result);
    }

    @Test
    void findAllAirlines(){
        //given
        List<Airline> airlines = new ArrayList<>();
        Airline turkishAirlines = Airline.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();
        airlines.add(turkishAirlines);

        Airline pegasusAirlines = Airline.builder()
                .id(PEGASUS_AIRLINES_ID)
                .airlineName(PEGASUS_AIRLINES_NAME)
                .build();
        airlines.add(pegasusAirlines);

        List<AirlineDto> airlineDtoList = new ArrayList<>();
        AirlineDto turkishAirlinesDto = AirlineDto.builder()
                .id(TURKISH_AIRLINES_ID)
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();
        airlineDtoList.add(turkishAirlinesDto);

        AirlineDto pegasusAirlinesDto = AirlineDto.builder()
                .id(PEGASUS_AIRLINES_ID)
                .airlineName(PEGASUS_AIRLINES_NAME)
                .build();
        airlineDtoList.add(pegasusAirlinesDto);

        //when
        given(airlineRepository.findAll()).willReturn(airlines);

        //then
        List<AirlineDto> result = airlineService.getAllAirlines();
        assertEquals(airlineDtoList, result);
    }

    @Test
    void findAirline_shouldFail() {
        given(airlineRepository.findById(TURKISH_AIRLINES_ID)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> airlineService.getAirlineById(TURKISH_AIRLINES_ID));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Airline could not be found.", throwable.getMessage());
    }

    @Test
    void saveAirline(){
        //given
        Airline airline = Airline.builder()
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        AirlineDto airlineDto = AirlineDto.builder()
                .airlineName(TURKISH_AIRLINES_NAME)
                .build();

        //when
        given(airlineRepository.save(airline)).willReturn(airline);

        //then
        AirlineDto result = airlineService.saveAirline(airlineDto);
        verify(airlineRepository).save(ArgumentMatchers.argThat(airline1 -> {
            assertEquals(airline, airline1);
            return true;
        }));

        assertEquals(airlineDto, result);
    }
}