package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.domain.Flight;
import com.finartz.flightticket.repository.AirlineRepository;
import com.finartz.flightticket.repository.FlightRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.FlightMapper;
import com.finartz.flightticket.web.model.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;
    private final FlightMapper flightMapper;

    @Override
    public FlightDto getFlightById(Long id) {
        return flightMapper.flightToFlightDto(flightRepository.findById(id)
                .orElseThrow(() -> new FlightException("Flight could not be found.")));
    }

    @Override
    public List<FlightDto> getAllFlightsByAirlineId(Long airlineId) {
        return flightRepository.getAllByAirline_Id(airlineId)
                .stream()
                .map(flightMapper::flightToFlightDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public FlightDto saveFlight(Long airlineId, FlightDto flightDto) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new FlightException("Airline could not be found."));

        Optional<Flight> foundFlight = flightRepository.findById(flightDto.getId());
        if (foundFlight.isPresent()){
            throw new FlightException("Flight is already exist.");
        }

        Flight flight = flightMapper.flightDtoToFlight(flightDto);
        flight.setAirline(airline);
        return flightMapper.flightToFlightDto(flightRepository.save(flight));
    }
}
