package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.domain.Route;
import com.finartz.flightticket.web.model.AirportDto;
import com.finartz.flightticket.web.model.RouteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteMapperTest {
    public static final String ISTANBUL_AIRPORT_NAME = "Istanbul Airport";
    public static final long ISTANBUL_AIRPORT_ID = 1L;
    public static final String HEATHROW_AIRPORT_NAME = "Heathrow Airport";
    public static final long HEATHROW_AIRPORT_ID = 2L;
    public static final long ROUTE_ID = 1L;
    RouteMapper routeMapper;

    @BeforeEach
    void setUp() {
        routeMapper = new RouteMapperImpl();
    }

    @Test
    void routeDtoToRoute() {
        //given
        RouteDto routeDto = RouteDto.builder()
                .id(ROUTE_ID)
                .departureLine(AirportDto.builder()
                        .id(ISTANBUL_AIRPORT_ID)
                        .airportName(ISTANBUL_AIRPORT_NAME)
                        .build())
                .arrivalLine(AirportDto.builder()
                        .id(HEATHROW_AIRPORT_ID)
                        .airportName(HEATHROW_AIRPORT_NAME)
                        .build())
                .build();

        //when
        //then
        Route route = routeMapper.routeDtoToRoute(routeDto);
        assertEquals(ROUTE_ID, route.getId());
        assertEquals(ISTANBUL_AIRPORT_ID, route.getDepartureLine().getId());
        assertEquals(ISTANBUL_AIRPORT_NAME, route.getDepartureLine().getAirportName());
        assertEquals(HEATHROW_AIRPORT_ID, route.getArrivalLine().getId());
        assertEquals(HEATHROW_AIRPORT_NAME, route.getArrivalLine().getAirportName());
    }

    @Test
    void routeToRouteDto() {
        //given
        Route route = Route.builder()
                .id(ROUTE_ID)
                .departureLine(Airport.builder()
                        .id(ISTANBUL_AIRPORT_ID)
                        .airportName(ISTANBUL_AIRPORT_NAME)
                        .build())
                .arrivalLine(Airport.builder()
                        .id(HEATHROW_AIRPORT_ID)
                        .airportName(HEATHROW_AIRPORT_NAME)
                        .build())
                .build();

        //when
        //then
        RouteDto routeDto = routeMapper.routeToRouteDto(route);
        assertEquals(ROUTE_ID, routeDto.getId());
        assertEquals(ISTANBUL_AIRPORT_ID, routeDto.getDepartureLine().getId());
        assertEquals(ISTANBUL_AIRPORT_NAME, routeDto.getDepartureLine().getAirportName());
        assertEquals(HEATHROW_AIRPORT_ID, routeDto.getArrivalLine().getId());
        assertEquals(HEATHROW_AIRPORT_NAME, routeDto.getArrivalLine().getAirportName());
    }
}