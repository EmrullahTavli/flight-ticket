package com.finartz.flightticket.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

class TicketPriceEngineTest {
    TicketPriceEngine ticketPriceEngine;

    @BeforeEach
    void setUp(){
        ticketPriceEngine = new TicketPriceEngine();
    }

    @Test
    void getTicketPrice(){
        BigDecimal ticketPrice = ticketPriceEngine.ticketPrice(BigDecimal.valueOf(100), 100, 10);
        assertThat(BigDecimal.valueOf(110), Matchers.comparesEqualTo(ticketPrice));
    }
}