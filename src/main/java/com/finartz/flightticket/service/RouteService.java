package com.finartz.flightticket.service;

import com.finartz.flightticket.web.model.RouteDto;

import java.util.List;

public interface RouteService {
    RouteDto getRouteById(Long id);

    List<RouteDto> getAllRoutes();

    RouteDto saveRoute(RouteDto routeDto);
}
