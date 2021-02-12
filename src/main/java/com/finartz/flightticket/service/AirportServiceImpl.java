package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.Airport;
import com.finartz.flightticket.repository.AirportRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.AirportMapper;
import com.finartz.flightticket.web.model.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public AirportDto getAirportById(Long id) {
        return airportMapper.airportToAirportDto(airportRepository.findById(id)
                .orElseThrow(() -> new FlightException("Airport could not be found.")));
    }

    @Override
    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(airportMapper::airportToAirportDto)
                .collect(Collectors.toList());
    }

    @Override
    public AirportDto saveAirport(AirportDto airportDto) {
        Optional<Airport> airport = airportRepository.findById(airportDto.getId());
        if (airport.isPresent()){
            throw new FlightException("Airport is already exist.");
        }

        return airportMapper.airportToAirportDto(airportRepository
                .save(airportMapper.airportDtoToAirport(airportDto)));
    }
}
