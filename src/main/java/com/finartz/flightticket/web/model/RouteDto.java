package com.finartz.flightticket.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDto {
    @NotNull
    private Long id;

    @NotNull
    @JsonProperty("departureLine")
    private AirportDto departureLine;

    @NotNull
    @JsonProperty("arrivalLine")
    private AirportDto arrivalLine;
}
