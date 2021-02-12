package com.finartz.flightticket.web.controller;

import com.finartz.flightticket.service.AirlineService;
import com.finartz.flightticket.web.model.AirlineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/airlines")
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping("/{airlineId}")
    public AirlineDto getAirline(@PathVariable Long airlineId){
        return airlineService.getAirlineById(airlineId);
    }

    @GetMapping
    public List<AirlineDto> getAirports(){
        return airlineService.getAllAirlines();
    }

    @PostMapping
    public AirlineDto saveAirline(@Valid @RequestBody AirlineDto airlineDto){
        return airlineService.saveAirline(airlineDto);
    }
}
