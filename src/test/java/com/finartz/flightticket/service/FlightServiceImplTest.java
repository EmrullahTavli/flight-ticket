package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.domain.Flight;
import com.finartz.flightticket.domain.Route;
import com.finartz.flightticket.repository.AirlineRepository;
import com.finartz.flightticket.repository.FlightRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.FlightMapper;
import com.finartz.flightticket.web.model.AirportDto;
import com.finartz.flightticket.web.model.FlightDto;
import com.finartz.flightticket.web.model.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class FlightServiceImplTest {
    @Mock
    FlightRepository flightRepository;

    @Mock
    AirlineRepository airlineRepository;

    FlightMapper flightMapper = FlightMapper.INSTANCE;

    FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightService = new FlightServiceImpl(flightRepository, airlineRepository, flightMapper);
    }

    @Test
    void findFlight() {
        //given
        long flightId = 1L;
        LocalDateTime date = LocalDateTime.now();

        Flight flight = getFlight();
        flight.setId(flightId);
        flight.setDate(date);

        FlightDto flightDto = getFlightDto();
        flightDto.setId(flightId);
        flightDto.setDate(date);

        //when
        given(flightRepository.findById(flight.getId())).willReturn(Optional.of(flight));

        //then
        FlightDto result = flightService.getFlightById(flightDto.getId());
        assertEquals(flightDto, result);
    }

    @Test
    void findAllFlightsByAirline() {
        //given
        long flightTK102Id = 1l;
        long flightTK103Id = 2l;
        LocalDateTime date = LocalDateTime.now();

        List<Flight> flights = new ArrayList<>();

        Flight flightTK102 = getFlight();
        flightTK102.setId(flightTK102Id);
        flightTK102.setDate(date);
        flights.add(flightTK102);

        Flight flightTK103 = getFlight();
        flightTK103.setId(flightTK103Id);
        flightTK103.setDate(date);
        flights.add(flightTK103);

        List<FlightDto> flightDtoList = new ArrayList<>();

        FlightDto flightTK102Dto = getFlightDto();
        flightTK102Dto.setId(flightTK102Id);
        flightTK102Dto.setDate(date);
        flightDtoList.add(flightTK102Dto);

        FlightDto flightTK103Dto = getFlightDto();
        flightTK103Dto.setId(flightTK103Id);
        flightTK103Dto.setDate(date);
        flightDtoList.add(flightTK103Dto);

        //when
        given(flightRepository.getAllByAirline_Id(flightTK102.getAirline().getId())).willReturn(flights);

        //then
        List<FlightDto> result = flightService.getAllFlightsByAirlineId(flightTK102.getAirline().getId());
        assertEquals(flightDtoList, result);
    }

    @Test
    void findFlight_shouldFail() {
        long flightId = 1L;
        given(flightRepository.findById(flightId)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> flightService.getFlightById(flightId));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Flight could not be found.", throwable.getMessage());
    }

    @Test
    void saveFlight() {
        //given
        LocalDateTime date = LocalDateTime.now();

        Flight flight = getFlight();
        flight.setDate(date);

        FlightDto flightDto = getFlightDto();
        flightDto.setDate(date);

        //when
        given(airlineRepository.findById(flight.getAirline().getId())).willReturn(Optional.of(flight.getAirline()));
        given(flightRepository.save(flight)).willReturn(flight);

        //then
        FlightDto result = flightService.saveFlight(flight.getAirline().getId(), flightDto);
        verify(flightRepository).save(ArgumentMatchers.argThat(flight1 -> {
            assertEquals(flight, flight1);
            return true;
        }));

        assertEquals(flightDto, result);
    }

    private FlightDto getFlightDto() {
        return FlightDto.builder()
                .flightNo("TK-102")
                .routeDto(RouteDto.builder()
                        .id(1L)
                        .departureLine(AirportDto.builder()
                                .id(1L)
                                .airportName("Istanbul Airport")
                                .build())
                        .arrivalLine(AirportDto.builder()
                                .id(2L)
                                .airportName("Heathrow Airport")
                                .build())
                        .build())
                .date(LocalDateTime.now())
                .flightPrice(BigDecimal.TEN)
                .quota(100)
                .build();
    }

    private Flight getFlight() {
        Airline airline = Airline.builder()
                .id(1L)
                .airlineName("Turkish Airlines")
                .build();

        return Flight.builder()
                .date(LocalDateTime.now())
                .flightNo("TK-102")
                .airline(airline)
                .route(Route.builder()
                        .id(1L)
                        .departureLine(Airport.builder()
                                .id(1L)
                                .airportName("Istanbul Airport")
                                .build())
                        .arrivalLine(Airport.builder()
                                .id(2L)
                                .airportName("Heathrow Airport")
                                .build())
                        .build())
                .flightPrice(BigDecimal.TEN)
                .quota(100)
                .build();
    }
}