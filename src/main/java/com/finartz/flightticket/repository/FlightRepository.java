package com.finartz.flightticket.repository;

import com.finartz.flightticket.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> getAllByAirline_Id(Long airlineId);
}
