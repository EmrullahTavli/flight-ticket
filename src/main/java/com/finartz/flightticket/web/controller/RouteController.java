package com.finartz.flightticket.web.controller;

import com.finartz.flightticket.service.RouteService;
import com.finartz.flightticket.web.model.RouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;

    @GetMapping("/{id}")
    public RouteDto getRota(@PathVariable Long id){
        return routeService.getRouteById(id);
    }

    @GetMapping
    public List<RouteDto> getAllRotas(){
        return routeService.getAllRoutes();
    }

    @PostMapping
    public RouteDto saveRota(@Valid @RequestBody RouteDto routeDto) {
        return routeService.saveRoute(routeDto);
    }
}
