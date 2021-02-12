package com.finartz.flightticket.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {
    @NotNull
    private Long id;

    @NotBlank
    private String flightNo;

    @NotNull
    @JsonProperty("route")
    private RouteDto routeDto;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @NotNull
    @Positive
    private Integer quota;

    @NotNull
    @Positive
    private BigDecimal flightPrice;
}
