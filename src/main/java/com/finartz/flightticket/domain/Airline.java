package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Airline extends BaseEntity{
    private String airlineName;

    @Builder
    public Airline(Long id, String airlineName) {
        super(id);
        this.airlineName = airlineName;
    }
}
