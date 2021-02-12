package com.finartz.flightticket.repository;

import com.finartz.flightticket.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
