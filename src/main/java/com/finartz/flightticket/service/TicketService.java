package com.finartz.flightticket.service;

import com.finartz.flightticket.web.model.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto getTicketById(Long id);

    List<TicketDto> getAllTicketsByFlightId(Long flightId);

    TicketDto buyTicket(Long airlineId, Long flightId, TicketDto ticketDto);

    void deleteTicket(Long id);
}
