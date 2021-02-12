package com.finartz.flightticket.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TicketPriceEngine {

    public BigDecimal ticketPrice(BigDecimal basedTicketPrice, long quota, long numberOfSoldTickets) {
        double increaseRate = (double) numberOfSoldTickets / (double) quota;
        return basedTicketPrice.multiply(BigDecimal.valueOf(1 + increaseRate));
    }
}
