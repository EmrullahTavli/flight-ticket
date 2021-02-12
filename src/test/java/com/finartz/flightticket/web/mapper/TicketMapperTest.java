package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.*;
import com.finartz.flightticket.web.model.TicketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketMapperTest {
    public static final String FLIGHT_NO = "TK-102";
    public static final long FIRST_ID = 1L;
    public static final String DEPARTURE_LINE_NAME = "Istanbul Airport";
    public static final long SECOND_ID = 2L;
    public static final String ARRIVAL_LINE_NAME = "Heathrow Airport";
    public static final String TURKISH_AIRLINES = "Turkish Airlines";
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String PNR_NO = "PNR";
    public static final String CREDIT_CARD_NUMBER = "1234567890123456";

    TicketMapper ticketMapper;

    @BeforeEach
    void setUp() {
        ticketMapper = new TicketMapperImpl();
    }

    @Test
    void ticketDtoToTicket() {
        TicketDto ticketDto = TicketDto.builder()
                .id(FIRST_ID)
                .flightNo(FLIGHT_NO)
                .pnrNo(PNR_NO)
                .airlineName(TURKISH_AIRLINES)
                .departureLine(DEPARTURE_LINE_NAME)
                .arrivalLine(ARRIVAL_LINE_NAME)
                .creditCardNumber(CREDIT_CARD_NUMBER)
                .dateTime(DATE)
                .build();

        Ticket ticket = ticketMapper.ticketDtoToTicket(ticketDto);
        assertEquals(FIRST_ID, ticket.getId());
        assertEquals(FLIGHT_NO, ticket.getFlight().getFlightNo());
        assertEquals(PNR_NO, ticket.getPnrNo());
        assertEquals(TURKISH_AIRLINES, ticket.getFlight().getAirline().getAirlineName());
        assertEquals(DEPARTURE_LINE_NAME, ticket.getFlight().getRoute().getDepartureLine().getAirportName());
        assertEquals(ARRIVAL_LINE_NAME, ticket.getFlight().getRoute().getArrivalLine().getAirportName());
        assertEquals(CREDIT_CARD_NUMBER, ticket.getPayment().getCreditCardNumber());
        assertEquals(DATE, ticket.getFlight().getDate());
    }

    @Test
    void ticketToTicketDto() {
        Airline airline = Airline.builder()
                .id(FIRST_ID)
                .airlineName(TURKISH_AIRLINES)
                .build();

        Ticket ticket = Ticket.builder()
                .id(FIRST_ID)
                .pnrNo(PNR_NO)
                .flight(Flight.builder()
                        .id(FIRST_ID)
                        .date(DATE)
                        .flightNo(FLIGHT_NO)
                        .airline(airline)
                        .route(Route.builder()
                                .id(FIRST_ID)
                                .departureLine(Airport.builder()
                                        .id(FIRST_ID)
                                        .airportName(DEPARTURE_LINE_NAME)
                                        .build())
                                .arrivalLine(Airport.builder()
                                        .id(SECOND_ID)
                                        .airportName(ARRIVAL_LINE_NAME)
                                        .build())
                                .build())
                        .flightPrice(BigDecimal.TEN)
                        .quota(100)
                        .build())
                .payment(Payment.builder()
                        .id(FIRST_ID)
                        .creditCardNumber(CREDIT_CARD_NUMBER)
                        .build())
                .build();

        TicketDto ticketDto = ticketMapper.ticketToTicketDto(ticket);
        assertEquals(FIRST_ID, ticketDto.getId());
        assertEquals(FLIGHT_NO, ticketDto.getFlightNo());
        assertEquals(PNR_NO, ticketDto.getPnrNo());
        assertEquals(TURKISH_AIRLINES, ticketDto.getAirlineName());
        assertEquals(DEPARTURE_LINE_NAME, ticketDto.getDepartureLine());
        assertEquals(ARRIVAL_LINE_NAME, ticketDto.getArrivalLine());
        assertEquals(CREDIT_CARD_NUMBER, ticketDto.getCreditCardNumber());
        assertEquals(DATE, ticketDto.getDateTime());

    }
}