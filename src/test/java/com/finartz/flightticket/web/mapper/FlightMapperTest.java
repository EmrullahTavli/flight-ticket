package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.domain.Flight;
import com.finartz.flightticket.domain.Route;
import com.finartz.flightticket.web.model.AirportDto;
import com.finartz.flightticket.web.model.FlightDto;
import com.finartz.flightticket.web.model.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightMapperTest {
    public static final String FLIGHT_NO = "TK-102";
    public static final long FIRST_ID = 1L;
    public static final String DEPARTURE_LINE_NAME = "Istanbul Airport";
    public static final long SECOND_ID = 2L;
    public static final String ARRIVAL_LINE_NAME = "Heathrow Airport";
    public static final String TURKISH_AIRLINES = "Turkish Airlines";
    public static final LocalDateTime DATE = LocalDateTime.now();

    FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        flightMapper = new FlightMapperImpl();
    }

    @Test
    void flightDtoToFlight() {
        FlightDto flightDto = FlightDto.builder()
                .id(FIRST_ID)
                .flightNo(FLIGHT_NO)
                .routeDto(RouteDto.builder()
                        .id(FIRST_ID)
                        .departureLine(AirportDto.builder()
                                .id(FIRST_ID)
                                .airportName(DEPARTURE_LINE_NAME)
                                .build())
                        .arrivalLine(AirportDto.builder()
                                .id(SECOND_ID)
                                .airportName(ARRIVAL_LINE_NAME)
                                .build())
                        .build())
                .date(DATE)
                .flightPrice(BigDecimal.TEN)
                .quota(100)
                .build();

        Flight flight = flightMapper.flightDtoToFlight(flightDto);
        assertEquals(FIRST_ID, flight.getId());
        assertEquals(FLIGHT_NO, flight.getFlightNo());
        assertEquals(FIRST_ID, flight.getRoute().getId());
        assertEquals(FIRST_ID, flight.getRoute().getDepartureLine().getId());
        assertEquals(DEPARTURE_LINE_NAME, flight.getRoute().getDepartureLine().getAirportName());
        assertEquals(SECOND_ID, flight.getRoute().getArrivalLine().getId());
        assertEquals(ARRIVAL_LINE_NAME, flight.getRoute().getArrivalLine().getAirportName());
        assertEquals(DATE, flight.getDate());
        assertEquals(BigDecimal.TEN, flight.getFlightPrice());
        assertEquals(100, flight.getQuota());
    }

    @Test
    void flightToFlightDto() {
        Airline airline = Airline.builder()
                .id(FIRST_ID)
                .airlineName(TURKISH_AIRLINES)
                .build();

        Flight flight = Flight.builder()
                .id(FIRST_ID)
                .date(DATE)
                .flightNo(FLIGHT_NO)
                .airline(airline)
                .route(Route.builder()
                        .id(FIRST_ID)
                        .departureLine(Airport.builder()
                                .id(FIRST_ID)
                                .airportName(DEPARTURE_LINE_NAME)
                                .build())
                        .arrivalLine(Airport.builder()
                                .id(SECOND_ID)
                                .airportName(ARRIVAL_LINE_NAME)
                                .build())
                        .build())
                .flightPrice(BigDecimal.TEN)
                .quota(100)
                .build();

        FlightDto flightDto = flightMapper.flightToFlightDto(flight);
        assertEquals(FIRST_ID, flightDto.getId());
        assertEquals(FLIGHT_NO, flightDto.getFlightNo());
        assertEquals(FIRST_ID, flightDto.getRouteDto().getId());
        assertEquals(FIRST_ID, flightDto.getRouteDto().getDepartureLine().getId());
        assertEquals(DEPARTURE_LINE_NAME, flightDto.getRouteDto().getDepartureLine().getAirportName());
        assertEquals(SECOND_ID, flightDto.getRouteDto().getArrivalLine().getId());
        assertEquals(ARRIVAL_LINE_NAME, flightDto.getRouteDto().getArrivalLine().getAirportName());
        assertEquals(DATE, flightDto.getDate());
        assertEquals(BigDecimal.TEN, flightDto.getFlightPrice());
        assertEquals(100, flightDto.getQuota());

    }
}