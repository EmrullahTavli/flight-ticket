package com.finartz.flightticket.repository;

import com.finartz.flightticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getTicketsByFlight_Id(Long flightId);

    long countByFlight_Id(Long flightId);
}
