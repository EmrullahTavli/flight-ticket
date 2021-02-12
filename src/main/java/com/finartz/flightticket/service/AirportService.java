package com.finartz.flightticket.service;

import com.finartz.flightticket.web.model.AirportDto;

import java.util.List;

public interface AirportService {
    AirportDto getAirportById(Long id);

    List<AirportDto> getAllAirports();

    AirportDto saveAirport(AirportDto airportDto);
}
