package com.finartz.flightticket.service;

import com.finartz.flightticket.domain.*;
import com.finartz.flightticket.repository.AirlineRepository;
import com.finartz.flightticket.repository.FlightRepository;
import com.finartz.flightticket.repository.TicketRepository;
import com.finartz.flightticket.web.exception.FlightException;
import com.finartz.flightticket.web.mapper.TicketMapper;
import com.finartz.flightticket.web.model.TicketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class TicketServiceImplTest {
    @Mock
    TicketRepository ticketRepository;

    @Mock
    FlightRepository flightRepository;

    @Mock
    AirlineRepository airlineRepository;

    @Mock
    TicketPriceEngine ticketPriceEngine;

    @Mock
    CreditCardEngine creditCardEngine;

    TicketMapper ticketMapper = TicketMapper.INSTANCE;

    TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketService = new TicketServiceImpl(ticketRepository, flightRepository, airlineRepository,
                ticketPriceEngine, creditCardEngine, ticketMapper);
    }

    @Test
    void findTicket() {
        //given
        Long ticketId = 1L;
        LocalDateTime date = LocalDateTime.now();
        Ticket ticket = getTicket();
        ticket.setId(ticketId);
        ticket.getFlight().setDate(date);

        TicketDto ticketDto = getTicketDto();
        ticketDto.setId(ticketId);
        ticketDto.setDateTime(date);

        //when
        given(ticketRepository.findById(any())).willReturn(Optional.of(ticket));

        //then
        TicketDto result = ticketService.getTicketById(ticketDto.getId());
        assertEquals(ticketDto, result);
    }

    @Test
    void findAllTicketsByFlight(){
        //given
        Long ticketId1 = 1L;
        Long ticketId2 = 2L;
        LocalDateTime date = LocalDateTime.now();

        List<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = getTicket();
        ticket1.setId(ticketId1);
        ticket1.getFlight().setDate(date);
        tickets.add(ticket1);

        Ticket ticket2 = getTicket();
        ticket2.setId(ticketId2);
        ticket2.getFlight().setDate(date);
        tickets.add(ticket2);

        List<TicketDto> ticketDtoList = new ArrayList<>();

        TicketDto ticketDto1 = getTicketDto();
        ticketDto1.setId(ticketId1);
        ticketDto1.setDateTime(date);
        ticketDtoList.add(ticketDto1);

        TicketDto ticketDto2 = getTicketDto();
        ticketDto2.setId(ticketId2);
        ticketDto2.setDateTime(date);
        ticketDtoList.add(ticketDto2);

        //given
        given(ticketRepository.getTicketsByFlight_Id(ticket1.getFlight().getId())).willReturn(tickets);

        //then
        List<TicketDto> result = ticketService.getAllTicketsByFlightId(ticket1.getFlight().getId());
        assertEquals(ticketDtoList, result);
    }

    @Test
    void findTicket_shouldFail() {
        Long ticketId = 1L;
        given(ticketRepository.findById(ticketId)).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> ticketService.getTicketById(ticketId));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Ticket could not be found.", throwable.getMessage());
    }

    @Test
    void saveTicket(){
        //given
        LocalDateTime date = LocalDateTime.now();
        Ticket ticket = getTicket();
        ticket.getFlight().setDate(date);

        TicketDto ticketDto = getTicketDto();
        ticketDto.setDateTime(date);

        //when
        given(airlineRepository.findById(ticket.getFlight().getAirline().getId()))
                .willReturn(Optional.of(ticket.getFlight().getAirline()));

        given(flightRepository.findById(ticket.getFlight().getId()))
                .willReturn(Optional.of(ticket.getFlight()));

        given(creditCardEngine.maskCreditCard(ticket.getPayment().getCreditCardNumber()))
                .willReturn(ticket.getPayment().getCreditCardNumber());

        given(ticketRepository.save(ticket)).willReturn(ticket);

        //then
        TicketDto result = ticketService.buyTicket(ticket.getFlight().getAirline().getId(),
                ticket.getFlight().getId(), ticketDto);
        verify(ticketRepository).save(ArgumentMatchers.argThat(ticket1 -> {
            assertEquals(ticket, ticket1);
            return true;
        }));

        assertEquals(ticketDto, result);
    }

    private Ticket getTicket() {
        return Ticket.builder()
                .pnrNo("PNR")
                .flight(getFlight())
                .payment(getPayment())
                .build();
    }

    private TicketDto getTicketDto() {
        return TicketDto.builder()
                .flightNo("TK-102")
                .pnrNo("PNR")
                .airlineName("Turkish Airlines")
                .departureLine("Istanbul Airport")
                .arrivalLine("Heathrow Airport")
                .creditCardNumber("1234567890123456")
                .build();
    }

    private Payment getPayment() {
        return Payment.builder()
                .id(1L)
                .creditCardNumber("1234567890123456")
                .build();
    }

    private Flight getFlight() {
        Airline airline = Airline.builder()
                .id(1L)
                .airlineName("Turkish Airlines")
                .build();

        return Flight.builder()
                .id(1L)
                .flightNo("TK-102")
                .airline(airline)
                .route(Route.builder()
                        .id(1L)
                        .departureLine(Airport.builder()
                                .id(1L)
                                .airportName("Istanbul Airport")
                                .build())
                        .arrivalLine(Airport.builder()
                                .id(2L)
                                .airportName("Heathrow Airport")
                                .build())
                        .build())
                .flightPrice(BigDecimal.TEN)
                .quota(100)
                .build();
    }
}