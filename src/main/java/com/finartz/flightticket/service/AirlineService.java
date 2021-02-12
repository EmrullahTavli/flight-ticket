package com.finartz.flightticket.service;

import com.finartz.flightticket.web.model.AirlineDto;

import java.util.List;

public interface AirlineService {
    AirlineDto getAirlineById(Long id);

    List<AirlineDto> getAllAirlines();

    AirlineDto saveAirline(AirlineDto airlineDto);
}
