package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airline;
import com.finartz.flightticket.repository.AirlineRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.AirlineMapper;
import com.finartz.flightticket.web.model.AirlineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Override
    public AirlineDto getAirlineById(Long id) {
        return airlineMapper.airlineToAirlineDto(airlineRepository.findById(id)
                        .orElseThrow(() -> new FlightException("Airline could not be found.")));
    }

    @Override
    public List<AirlineDto> getAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(airlineMapper::airlineToAirlineDto)
                .collect(Collectors.toList());
    }

    @Override
    public AirlineDto saveAirline(AirlineDto airlineDto) {
        Optional<Airline> airline = airlineRepository.findById(airlineDto.getId());
        if (airline.isPresent()){
            throw new FlightException("Airline is already exist.");
        }
        return airlineMapper.airlineToAirlineDto(airlineRepository.save(airlineMapper.airlineDtoToAirline(airlineDto)));
    }
}
