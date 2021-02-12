package com.finartz.flightticket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Payment extends BaseEntity {
    private String creditCardNumber;

    @Builder
    public Payment(Long id, String creditCardNumber) {
        super(id);
        this.creditCardNumber = creditCardNumber;
    }
}
