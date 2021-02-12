package com.finartz.flightticket.web.mapper;

import com.finartz.flightticket.domain.Ticket;
import com.finartz.flightticket.web.model.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mappings({
            @Mapping(target = "flight.airline.airlineName", source = "airlineName"),
            @Mapping(target = "flight.flightNo", source = "flightNo"),
            @Mapping(target = "flight.route.departureLine.airportName", source = "departureLine"),
            @Mapping(target = "flight.route.arrivalLine.airportName", source = "arrivalLine"),
            @Mapping(target = "flight.date", source = "dateTime"),
            @Mapping(target = "payment.creditCardNumber", source = "creditCardNumber")
    })
    Ticket ticketDtoToTicket(TicketDto ticketDto);

    @Mappings({
            @Mapping(target = "airlineName", source = "flight.airline.airlineName"),
            @Mapping(target = "flightNo", source = "flight.flightNo"),
            @Mapping(target = "departureLine", source = "flight.route.departureLine.airportName"),
            @Mapping(target = "arrivalLine", source = "flight.route.arrivalLine.airportName"),
            @Mapping(target = "dateTime", source = "flight.date"),
            @Mapping(target = "creditCardNumber", source = "payment.creditCardNumber")
    })
    TicketDto ticketToTicketDto(Ticket ticket);
}