package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class Ticket extends BaseEntity {
    private String pnrNo;

    @ManyToOne
    private Flight flight;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @Builder
    public Ticket(Long id, String pnrNo, Flight flight, Payment payment) {
        super(id);
        this.pnrNo = pnrNo;
        this.flight = flight;
        this.payment = payment;
    }
}
