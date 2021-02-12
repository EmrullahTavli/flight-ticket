package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.web.model.AirlineDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirlineMapper {
    AirlineMapper INSTANCE = Mappers.getMapper(AirlineMapper.class);

    Airline airlineDtoToAirline(AirlineDto airlineDto);

    AirlineDto airlineToAirlineDto(Airline airline);
}
