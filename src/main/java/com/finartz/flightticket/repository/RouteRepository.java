package com.finartz.flightticket.repository;

import com.finartz.flightticket.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
