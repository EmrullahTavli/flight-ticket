package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.domain.Route;
import com.finartz.flightticket.repository.RouteRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.RouteMapper;
import com.finartz.flightticket.web.model.AirportDto;
import com.finartz.flightticket.web.model.RouteDto;
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

class RouteServiceImplTest {
    @Mock
    RouteRepository routeRepository;

    RouteMapper routeMapper = RouteMapper.INSTANCE;

    RouteService routeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        routeService = new RouteServiceImpl(routeRepository, routeMapper);
    }

    @Test
    void findRoute() {
        //given
        long routeId = 1L;
        Route route = getRoute();
        route.setId(routeId);

        RouteDto routeDto = getRouteDto();
        routeDto.setId(routeId);

        //when
        given(routeRepository.findById(route.getId())).willReturn(Optional.of(route));

        //then
        RouteDto result = routeService.getRouteById(routeDto.getId());
        assertEquals(routeDto, result);
    }

    @Test
    void findAllRoutes() {
        //given
        long routeIslId = 1L;
        long routeLhrId = 2L;

        List<Route> routes = new ArrayList<>();

        Route routeIsl = getRoute();
        routeIsl.setId(routeIslId);
        routes.add(routeIsl);

        Route routeLhr = getRoute();
        routeLhr.setId(routeLhrId);
        routes.add(routeLhr);

        List<RouteDto> routeDtoList = new ArrayList<>();

        RouteDto routeIslDto = getRouteDto();
        routeIslDto.setId(routeIslId);
        routeDtoList.add(routeIslDto);

        RouteDto routeLhrDto = getRouteDto();
        routeLhrDto.setId(routeLhrId);
        routeDtoList.add(routeLhrDto);

        //when
        given(routeRepository.findAll()).willReturn(routes);

        //then
        List<RouteDto> result = routeService.getAllRoutes();
        assertEquals(routeDtoList, result);
    }

    @Test
    void findRoute_shouldFail() {
        Long routeId = 1L;
        given(routeRepository.findById(routeId)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> routeService.getRouteById(routeId));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Route could not be found.", throwable.getMessage());
    }

    @Test
    void saveRoute() {
        //given
        Route route = getRoute();

        RouteDto routeDto = getRouteDto();

        //when
        given(routeRepository.save(route)).willReturn(route);

        //then
        RouteDto result = routeService.saveRoute(routeDto);
        verify(routeRepository).save(ArgumentMatchers.argThat(route1 -> {
            assertEquals(route, route1);
            return true;
        }));

        assertEquals(routeDto, result);
    }

    private RouteDto getRouteDto() {
        return RouteDto.builder()
                .departureLine(AirportDto.builder()
                        .id(1L)
                        .airportName("Istanbul Airport")
                        .build())
                .arrivalLine(AirportDto.builder()
                        .id(2L)
                        .airportName("Heathrow Airport")
                        .build())
                .build();
    }

    private Route getRoute() {
        return Route.builder()
                .departureLine(Airport.builder()
                        .id(1L)
                        .airportName("Istanbul Airport")
                        .build())
                .arrivalLine(Airport.builder()
                        .id(2L)
                        .airportName("Heathrow Airport")
                        .build())
                .build();
    }
}