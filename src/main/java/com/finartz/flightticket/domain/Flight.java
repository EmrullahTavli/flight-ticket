package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Flight extends BaseEntity {
    private String flightNo;

    @ManyToOne
    private Airline airline;

    @OneToOne
    private Route route;

    private LocalDateTime date;

    private Integer quota;

    private BigDecimal basedFlightPrice;

    private BigDecimal flightPrice;

    @Builder
    public Flight(Long id, String flightNo, Airline airline, Route route, LocalDateTime date,
                  Integer quota, BigDecimal basedFlightPrice, BigDecimal flightPrice) {
        super(id);
        this.flightNo = flightNo;
        this.airline = airline;
        this.route = route;
        this.date = date;
        this.quota = quota;
        this.basedFlightPrice = basedFlightPrice;
        this.flightPrice = flightPrice;
    }
}
