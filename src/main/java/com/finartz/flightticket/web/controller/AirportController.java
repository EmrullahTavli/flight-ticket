package com.finartz.flightticket.web.controller;

import com.finartz.flightticket.service.AirportService;
import com.finartz.flightticket.web.model.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;

    @GetMapping
    public List<AirportDto> getAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public AirportDto getAirport(@PathVariable Long id){
        return airportService.getAirportById(id);
    }

    @PostMapping
    public AirportDto saveAirport(@Valid @RequestBody AirportDto airportDto){
        return airportService.saveAirport(airportDto);
    }
}
