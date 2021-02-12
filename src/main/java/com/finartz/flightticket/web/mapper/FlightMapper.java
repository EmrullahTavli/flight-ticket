package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Flight;
import com.finartz.flightticket.web.model.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "route", source = "routeDto")
    Flight flightDtoToFlight(FlightDto flightDto);


    @Mapping(target = "routeDto", source = "route")
    FlightDto flightToFlightDto(Flight flight);
}
