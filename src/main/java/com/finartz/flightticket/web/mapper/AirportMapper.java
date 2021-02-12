package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.web.model.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirportMapper {
    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    Airport airportDtoToAirport(AirportDto airportDto);

    AirportDto airportToAirportDto(Airport airport);
}
