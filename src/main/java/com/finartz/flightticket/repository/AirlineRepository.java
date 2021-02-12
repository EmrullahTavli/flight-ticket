package com.finartz.flightticket.repository;

import com.finartz.flightticket.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
