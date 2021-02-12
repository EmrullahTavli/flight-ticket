package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Airport extends BaseEntity {
    private String airportName;

    @Builder
    public Airport(Long id, String airportName) {
        super(id);
        this.airportName = airportName;
    }
}
