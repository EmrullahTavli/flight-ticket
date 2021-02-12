package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class Route extends BaseEntity {
    @OneToOne
    private Airport departureLine;

    @OneToOne
    private Airport arrivalLine;

    @Builder
    public Route(Long id, Airport departureLine, Airport arrivalLine) {
        super(id);
        this.departureLine = departureLine;
        this.arrivalLine = arrivalLine;
    }
}
