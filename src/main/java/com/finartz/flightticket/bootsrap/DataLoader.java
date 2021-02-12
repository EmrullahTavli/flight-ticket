package com.finartz.flightticket.bootsrap;

import com.finartz.flightticket.domain.*;
import com.finartz.flightticket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final RouteRepository routeRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (airportRepository.count() == 0) {
            Airport islAirport = Airport.builder()
                    .airportName("Istanbul Airport")
                    .build();

            airportRepository.save(islAirport);

            Airport lhrAirport = Airport.builder()
                    .airportName("Heathrow Airport")
                    .build();

            airportRepository.save(lhrAirport);

            Airline turkishAirlines = Airline.builder()
                    .airlineName("Turkish Airlines")
                    .build();

            airlineRepository.save(turkishAirlines);

            Route islToLhr = Route.builder()
                    .departureLine(islAirport)
                    .arrivalLine(lhrAirport)
                    .build();

            routeRepository.save(islToLhr);

            Flight flight = Flight.builder()
                    .airline(turkishAirlines)
                    .flightNo("TK-102")
                    .quota(100)
                    .basedFlightPrice(new BigDecimal(100))
                    .flightPrice(new BigDecimal(100))
                    .route(islToLhr)
                    .date(LocalDateTime.now())
                    .build();

            flightRepository.save(flight);

            Payment payment = Payment.builder()
                    .creditCardNumber("123456******3456")
                    .build();

            Ticket ticket = Ticket.builder()
                    .flight(flight)
                    .pnrNo("PNR")
                    .payment(payment)
                    .build();

            ticketRepository.save(ticket);
        }
    }
}
