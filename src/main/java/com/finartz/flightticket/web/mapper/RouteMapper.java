package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Route;
import com.finartz.flightticket.web.model.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    Route routeDtoToRoute(RouteDto routeDto);

    RouteDto routeToRouteDto(Route route);
}
